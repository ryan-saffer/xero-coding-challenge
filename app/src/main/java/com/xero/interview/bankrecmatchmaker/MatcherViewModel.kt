package com.xero.interview.bankrecmatchmaker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xero.interview.bankrecmatchmaker.model.MatchItem

class MatcherViewModel : ViewModel() {

    private val _items = MutableLiveData(buildMockData())
    val items: LiveData<List<MatchItem>> get() = _items

    private val _selectedItems = MutableLiveData(setOf<MatchItem>())
    val selectedItems: LiveData<Set<MatchItem>> get() = _selectedItems

    /**
     * Adds an item to the list of selected items, or removes it if it's already in there.
     */
    fun selectItem(item: MatchItem) {
        // TODO: work out why `.value` here is nullable. It should not be, given the live data is initialised with a list.
        val currentSet = _selectedItems.value?.toMutableSet() ?: mutableSetOf()
        if (currentSet.contains(item)) {
            currentSet.remove(item)
        } else {
            currentSet.add(item)
        }
        _selectedItems.value = currentSet
    }

    private fun buildMockData(): List<MatchItem> {
        return listOf(
            MatchItem("City Limousines", "30 Aug", 249.00f, "Sales Invoice"),
            MatchItem("Ridgeway University", "12 Sep", 618.50f, "Sales Invoice"),
            MatchItem("Cube Land", "22 Sep", 495.00f, "Sales Invoice"),
            MatchItem("Bayside Club", "23 Sep", 234.00f, "Sales Invoice"),
            MatchItem("SMART Agency", "12 Sep", 250f, "Sales Invoice"),
            MatchItem("PowerDirect", "11 Sep", 108.60f, "Sales Invoice"),
            MatchItem("PC Complete", "17 Sep", 216.99f, "Sales Invoice"),
            MatchItem("Truxton Properties", "17 Sep", 181.25f, "Sales Invoice"),
            MatchItem("MCO Cleaning Services", "17 Sep", 170.50f, "Sales Invoice"),
            MatchItem("Gateway Motors", "18 Sep", 411.35f, "Sales Invoice"),
        )
    }
}