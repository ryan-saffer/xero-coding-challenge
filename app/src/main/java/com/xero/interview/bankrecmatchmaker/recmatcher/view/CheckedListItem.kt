package com.xero.interview.bankrecmatchmaker.recmatcher.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Checkable
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatCheckBox
import com.xero.interview.bankrecmatchmaker.recmatcher.MatcherViewModel
import com.xero.interview.bankrecmatchmaker.R
import com.xero.interview.bankrecmatchmaker.recmatcher.model.MatchItem

class CheckedListItem : LinearLayout, Checkable {

    private lateinit var viewModel: MatcherViewModel
    private lateinit var matchItem: MatchItem
    private lateinit var checkBox: AppCompatCheckBox

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    private fun init(context: Context) {
        val layoutInflater = LayoutInflater.from(context)
        orientation = HORIZONTAL
        checkBox =
            layoutInflater.inflate(R.layout.list_item_checkbox, this, false) as AppCompatCheckBox
        addView(checkBox, 0)
        setOnClickListener {
            checkBox.toggle()
            viewModel.toggleItem(this.matchItem)
        }
    }

    fun setViewModel(viewModel: MatcherViewModel) {
        this.viewModel = viewModel
    }

    fun setMatchItem(matchItem: MatchItem) {
        this.matchItem = matchItem;
    }

    override fun setChecked(checked: Boolean) {
        checkBox.isChecked = checked
    }

    override fun isChecked(): Boolean {
        return checkBox.isChecked;
    }

    override fun toggle() {
        checkBox.toggle()
    }
}