package com.xero.interview.bankrecmatchmaker.recmatcher.repositories

import com.xero.interview.bankrecmatchmaker.recmatcher.model.MatchItem

class RecAutoMatcherRepositoryImpl : RecAutoMatcherRepository {
    override fun findSingleMatch(matchItems: List<MatchItem>, targetValue: Float) =
        matchItems.find { it.total == targetValue }
}