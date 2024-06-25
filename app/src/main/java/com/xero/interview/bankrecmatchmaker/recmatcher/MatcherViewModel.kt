package com.xero.interview.bankrecmatchmaker.recmatcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xero.interview.bankrecmatchmaker.recmatcher.model.MatchItem
import com.xero.interview.bankrecmatchmaker.recmatcher.repositories.RecRepository
import com.xero.interview.bankrecmatchmaker.recmatcher.repositories.RecAutoMatcherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MatcherViewModel @Inject constructor(
    recRepository: RecRepository,
    private val recAutoMatcherRepository: RecAutoMatcherRepository
) : ViewModel(
) {

    // The target value for all reconciliation items to add up to
    private val _targetMatchValue = MutableLiveData(recRepository.getMatchTargetValue())
    val targetMatchValue: LiveData<Float> get() = _targetMatchValue

    // All reconciliation items
    private val _items = MutableLiveData(recRepository.getMatchItems())
    val items: LiveData<List<MatchItem>> get() = _items

    // A hashmap of all selected items. Hashmap is used for quick lookup of whether an item is selected.
    private val _selectedItems = MutableLiveData(this.autoMatchItem() ?: hashMapOf())
    val selectedItems: LiveData<HashMap<String, MatchItem>> get() = _selectedItems

    /**
     * Determines if a matchItem is selected.
     */
    fun isItemSelected(matchItem: MatchItem) =
        _selectedItems.value?.containsKey(matchItem.id) ?: false

    /**
     * Adds an item to the list of selected items, or removes it if it's already in there.
     * Updates the target match value.
     */
    fun toggleItem(matchItem: MatchItem) {
        val currentMap = _selectedItems.value!!
        if (currentMap.containsKey(matchItem.id)) {
            currentMap.remove(matchItem.id)
            _targetMatchValue.value = _targetMatchValue.value!! + matchItem.total
        } else {
            currentMap[matchItem.id] = matchItem
            _targetMatchValue.value = _targetMatchValue.value!! - matchItem.total
        }
        _selectedItems.value = currentMap
    }

    /**
     * Asks the auto matcher repository for a single item match, and updates the state if one is found.
     */
    private fun autoMatchItem(): HashMap<String, MatchItem>? {
        val matchItem =
            recAutoMatcherRepository.findSingleMatch(_items.value!!, _targetMatchValue.value!!)
        return if (matchItem == null) {
            null
        } else {
            _targetMatchValue.value = 0f
            hashMapOf<String, MatchItem>().apply {
                this[matchItem.id] = matchItem
            }
        }
    }
}