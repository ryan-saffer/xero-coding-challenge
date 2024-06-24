package com.xero.interview.bankrecmatchmaker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xero.interview.bankrecmatchmaker.model.MatchItem

class MatcherViewModel : ViewModel() {

    private val _items = MutableLiveData(buildMockData())
    val items: LiveData<List<MatchItem>> get() = _items

    private val _selectedItems = MutableLiveData(listOf<MatchItem>())
    val selectedItems: LiveData<List<MatchItem>> get() = _selectedItems

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