package com.xero.interview.bankrecmatchmaker.recmatcher.repositories

import com.xero.interview.bankrecmatchmaker.recmatcher.model.MatchItem

/**
 * This repository is for all logic related to matching reconciliation items, such as finding a single match, or a subset, that add up to a total value.
 */
interface RecAutoMatcherRepository {

    /**
     * Given a list of match items and a target value, returns the match item that equals the value exactly, or null otherwise
     */
    fun findSingleMatch(matchItems: List<MatchItem>, targetValue: Float): MatchItem?
}