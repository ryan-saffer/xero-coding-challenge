package com.xero.interview.bankrecmatchmaker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xero.interview.bankrecmatchmaker.model.MatchItem

class MatcherViewModel : ViewModel() {

    companion object {
        const val DEFAULT_TARGET_MATCH_VALUE = 10_000f;
    }

    private val _targetMatchValue = MutableLiveData(DEFAULT_TARGET_MATCH_VALUE)
    val targetMatchValue: LiveData<Float> get() = _targetMatchValue

    private val _items = MutableLiveData(buildMockData())
    val items: LiveData<List<MatchItem>> get() = _items

    private val _selectedItems = MutableLiveData(hashMapOf<String, MatchItem>())
    val selectedItems: LiveData<HashMap<String, MatchItem>> get() = _selectedItems

    /**
     * Adds an item to the list of selected items, or removes it if it's already in there.
     * Updates the target match value.
     */
    fun toggleItem(matchItem: MatchItem) {
        val currentMap = _selectedItems.value ?: hashMapOf()
        if (currentMap.containsKey(matchItem.id)) {
            currentMap.remove(matchItem.id)
            _targetMatchValue.value = _targetMatchValue.value!! + matchItem.total
        } else {
            currentMap[matchItem.id] = matchItem
            _targetMatchValue.value = _targetMatchValue.value!! - matchItem.total
        }
        _selectedItems.value = currentMap
    }

    private fun buildMockData(): List<MatchItem> {
        return listOf(
            MatchItem("1", "City Limousines", "30 Aug", 249.00f, "Sales Invoice"),
            MatchItem("2", "Ridgeway University", "12 Sep", 618.50f, "Sales Invoice"),
            MatchItem("3", "Cube Land", "22 Sep", 495.00f, "Sales Invoice"),
            MatchItem("4", "Bayside Club", "23 Sep", 234.00f, "Sales Invoice"),
            MatchItem("5", "SMART Agency", "12 Sep", 250f, "Sales Invoice"),
            MatchItem("6", "PowerDirect", "11 Sep", 108.60f, "Sales Invoice"),
            MatchItem("7", "PC Complete", "17 Sep", 216.99f, "Sales Invoice"),
            MatchItem("8", "Truxton Properties", "17 Sep", 181.25f, "Sales Invoice"),
            MatchItem("9", "MCO Cleaning Services", "17 Sep", 170.50f, "Sales Invoice"),
            MatchItem("10", "Gateway Motors", "18 Sep", 411.35f, "Sales Invoice"),
        )
    }
}