package com.xero.interview.bankrecmatchmaker.RecMatcher.repositories

import com.xero.interview.bankrecmatchmaker.RecMatcher.model.MatchItem

interface RecRepository {

    fun getMatchItems(): List<MatchItem>
}