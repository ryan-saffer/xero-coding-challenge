package com.xero.interview.bankrecmatchmaker.recmatcher.repositories

import com.xero.interview.bankrecmatchmaker.recmatcher.model.MatchItem

interface RecAutoMatcherRepository {

    // given a list of match items and a target value, returns the match item that equals the value exactly, or null otherwise
    fun findSingleMatch(matchItems: List<MatchItem>, targetValue: Float): MatchItem?
}