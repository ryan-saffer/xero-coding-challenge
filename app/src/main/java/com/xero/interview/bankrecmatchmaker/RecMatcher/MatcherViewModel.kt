package com.xero.interview.bankrecmatchmaker.RecMatcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xero.interview.bankrecmatchmaker.RecMatcher.model.MatchItem
import com.xero.interview.bankrecmatchmaker.RecMatcher.repositories.RecRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MatcherViewModel @Inject constructor(
    private val recRepository: RecRepository,
) : ViewModel(
) {

    companion object {
        const val DEFAULT_TARGET_MATCH_VALUE = 10_000f;
    }

    private val _targetMatchValue = MutableLiveData(DEFAULT_TARGET_MATCH_VALUE)
    val targetMatchValue: LiveData<Float> get() = _targetMatchValue

    fun setTargetMatchValue(targetMatchValue: Float) {
        this._targetMatchValue.value = targetMatchValue
    }

    private val _items = MutableLiveData(recRepository.getMatchItems())
    val items: LiveData<List<MatchItem>> get() = _items

    private val _selectedItems = MutableLiveData(hashMapOf<String, MatchItem>())
    val selectedItems: LiveData<HashMap<String, MatchItem>> get() = _selectedItems

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
}