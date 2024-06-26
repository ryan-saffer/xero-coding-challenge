package com.xero.interview.bankrecmatchmaker

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.xero.interview.bankrecmatchmaker.recmatcher.MatcherViewModel
import com.xero.interview.bankrecmatchmaker.recmatcher.model.MatchItem
import com.xero.interview.bankrecmatchmaker.recmatcher.repositories.RecAutoMatcherRepository
import com.xero.interview.bankrecmatchmaker.recmatcher.repositories.RecRepository
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

class MatcherViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    val testItems = listOf(
        MatchItem("1", "Paid to 1", "Date 1", 100f, "docType1"),
        MatchItem("2", "Paid to 2", "Date 2", 200f, "docType2"),
        MatchItem("3", "Paid to 3", "Date 3", 300f, "docType3"),
        MatchItem("4", "Paid to 4", "Date 4", 400f, "docType4"),
        MatchItem("5", "Paid to 5", "Date 5", 500f, "docType5")
    )

    private val mockRecRepository = object : RecRepository {
        override fun getMatchTargetValue() = 10_000f
        override fun getMatchItems() = testItems
    }

    private val mockRecAutoMatcherRepository = object : RecAutoMatcherRepository {
        override fun findSingleMatch(matchItems: List<MatchItem>, targetValue: Float) = null
    }

    private fun createTestSubject(
        recRepository: RecRepository = mockRecRepository,
        recAutoMatcherRepository: RecAutoMatcherRepository = mockRecAutoMatcherRepository
    ) = MatcherViewModel(recRepository, recAutoMatcherRepository)

    @Test
    fun givenViewModelCreated_thenItemsCorrectlySet() {
        // given
        val testSubject = createTestSubject()

        // then
        testSubject.items.value!!.first().apply {
            assert(this.id == "1")
            assert(this.paidTo == "Paid to 1")
            assert(this.transactionDate == "Date 1")
            assert(this.total == 100f)
            assert(this.docType == "docType1")

        }
    }

    @Test
    fun givenViewModelCreated_thenSelectedItemsIsEmpty() {
        // given
        val testSubject = createTestSubject()

        // then
        assert(testSubject.selectedItems.value.isNullOrEmpty())
    }

    @Test
    fun givenMatchItemNotSelected_whenCheckingIfSelected_thenReturnFalse() {
        // given
        val testSubject = createTestSubject()

        // when
        val result = testSubject.isItemSelected(testItems[0])

        // then
        assertFalse(result)
    }

    @Test
    fun givenMatchItemSelected_whenCheckingIfSelected_thenReturnTrue() {
        // given
        val testSubject = createTestSubject()
        testSubject.toggleItem(testItems[0])

        // when
        val result = testSubject.isItemSelected(testItems[0])

        // then
        assertTrue(result)
    }

    @Test
    fun givenListOfItems_whenSingleItemToggled_thenMapCorrectlyUpdated() {
        // given
        val testSubject = createTestSubject()

        // when
        testSubject.toggleItem(testItems[0])

        // then
        assert(testSubject.selectedItems.value!!.containsKey("1"))
        assert(testSubject.selectedItems.value!!["1"] == testItems[0])

        // when
        testSubject.toggleItem(testItems[0])

        // then
        assert(testSubject.selectedItems.value!!.isEmpty())
    }

    @Test
    fun givenListOfItems_whenManyItemsToggled_thenMapCorrectlyUpdated() {
        // given
        val testSubject = createTestSubject()

        // then
        assert(testSubject.selectedItems.value!!.isEmpty())

        // when - add 4 items
        testSubject.toggleItem(testItems[0])
        testSubject.toggleItem(testItems[1])
        testSubject.toggleItem(testItems[2])
        testSubject.toggleItem(testItems[3])


        // then - there are 4 selected items
        assert(testSubject.selectedItems.value!!.size == 4)
        assert(testSubject.selectedItems.value!!.containsKey("1"))
        assert(testSubject.selectedItems.value!!["1"] == testItems[0])
        assert(testSubject.selectedItems.value!!.containsKey("2"))
        assert(testSubject.selectedItems.value!!["2"] == testItems[1])
        assert(testSubject.selectedItems.value!!.containsKey("3"))
        assert(testSubject.selectedItems.value!!["3"] == testItems[2])
        assert(testSubject.selectedItems.value!!.containsKey("4"))
        assert(testSubject.selectedItems.value!!["4"] == testItems[3])

        // when - toggle 2 items already toggled on
        testSubject.toggleItem(testItems[1])
        testSubject.toggleItem(testItems[3])

        // then - now only 2 items left, and those toggled off are gone
        assert(testSubject.selectedItems.value!!.size == 2)
        assert(!testSubject.selectedItems.value!!.containsKey("2"))
        assert(!testSubject.selectedItems.value!!.containsKey("4"))
    }

    @Test
    fun givenListOfItems_whenItemsToggled_targetMatchValueChanges() {
        // given
        val testSubject = createTestSubject()

        // then - target match value is initialised according to mock repository
        assert(testSubject.targetMatchValue.value == 10_000f)

        // when
        testSubject.toggleItem(testItems[0]) // removes 100
        testSubject.toggleItem(testItems[1]) // removes 200
        testSubject.toggleItem(testItems[2]) // removes 300
        testSubject.toggleItem(testItems[3]) // removes 400

        // then
        assert(testSubject.targetMatchValue.value == 9_000f)

        // when
        testSubject.toggleItem(testItems[0]) // adds 100
        testSubject.toggleItem(testItems[1]) // adds 200

        // then
        assert(testSubject.targetMatchValue.value == 9_300f)
    }

    @Test
    fun givenListOfItems_whenTogglingItemWithAmountHigherThanTargetMatch_thenItGoesIntoNegatives() {
        // given
        val testSubject =
            createTestSubject(recRepository = object : RecRepository by mockRecRepository {
                override fun getMatchTargetValue() = 500f
            })


        // when
        testSubject.toggleItem(testItems[2]) // removes 300
        testSubject.toggleItem(testItems[3]) // removes 400

        // then
        assert(testSubject.targetMatchValue.value == -200f)
    }

    @Test
    fun givenNoSingleMatchExists_whenAutoMatchingSingleItem_thenSelectedItemsIsEmpty_andTargetValueUnchanged() {
        // given
        val testSubject = createTestSubject(
            recAutoMatcherRepository = object : RecAutoMatcherRepository {
                override fun findSingleMatch(
                    matchItems: List<MatchItem>,
                    targetValue: Float
                ) = null
            }
        )

        // then
        assert(testSubject.selectedItems.value!!.isEmpty())

        // target value is unchanged
        assert(testSubject.targetMatchValue.value == 10_000f)
    }

    @Test
    fun givenSingleMatchExists_whenAutoMatchingSingleItem_thenSelectedItemsIncludesIt_andTargetValueSetToZero() {
        // given
        val testSubject = createTestSubject(
            recAutoMatcherRepository = object : RecAutoMatcherRepository {
                override fun findSingleMatch(
                    matchItems: List<MatchItem>,
                    targetValue: Float
                ) = testItems.first()
            }
        )

        // then
        assert(testSubject.selectedItems.value!!.size == 1)
        assert(testSubject.selectedItems.value!!.containsKey("1"))
        assert(testSubject.selectedItems.value!!["1"] == testItems.first())

        // target value has been set to 0
        assert(testSubject.targetMatchValue.value == 0f)
    }
}