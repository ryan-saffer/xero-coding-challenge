package com.xero.interview.bankrecmatchmaker.recmatcher.repositories

import com.xero.interview.bankrecmatchmaker.recmatcher.model.MatchItem

interface RecRepository {

    fun getMatchTargetValue(): Float

    fun getMatchItems(): List<MatchItem>
}