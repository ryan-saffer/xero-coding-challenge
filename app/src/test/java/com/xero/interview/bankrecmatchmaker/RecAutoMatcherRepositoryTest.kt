package com.xero.interview.bankrecmatchmaker

import com.xero.interview.bankrecmatchmaker.recmatcher.model.MatchItem
import com.xero.interview.bankrecmatchmaker.recmatcher.repositories.RecAutoMatcherRepositoryImpl
import org.junit.Test
import org.junit.Assert.assertNull

class RecAutoMatcherRepositoryTest {

    private val testItems = listOf(
        MatchItem("1", "Paid to 1", "date 1", 100f, "doc type 1"),
        MatchItem("2", "Paid to 2", "date 2", 200f, "doc type 2"),
        MatchItem("3", "Paid to 3", "date 3", 300f, "doc type 3"),
        MatchItem("4", "Paid to 4", "date 4", 400f, "doc type 4"),
        MatchItem("5", "Paid to 5", "date 5", 500f, "doc type 5"),
        MatchItem("6", "Paid to 6", "date 6", 200f, "doc type 6"),
    )

    @Test
    fun givenNoSingleMatches_whenFindSingleMatch_thenReturnNull() {
        // given
        val testSubject = RecAutoMatcherRepositoryImpl()

        // when
        val result = testSubject.findSingleMatch(
            matchItems = testItems,
            targetValue = 1_000f
        )

        // then
        assertNull(result)
    }

    @Test
    fun givenMatchFound_whenFindSingleMatch_thenReturnMatchItem() {
        // given
        val testSubject = RecAutoMatcherRepositoryImpl()

        // when
        val result = testSubject.findSingleMatch(
            matchItems = testItems,
            targetValue = 300f
        )

        // then
        assert(result == testItems[2])
    }

    @Test
    fun givenMultipleMatchesFound_whenFindingSingleMatch_thenReturnFirstItem() {
        // given
        val testSubject = RecAutoMatcherRepositoryImpl()

        // when
        val result = testSubject.findSingleMatch(
            matchItems = testItems,
            targetValue = 200f // matches item at position [1] and [5]
        )

        // then
        assert(result == testItems[1])
        assert(result != testItems[5])
    }
}