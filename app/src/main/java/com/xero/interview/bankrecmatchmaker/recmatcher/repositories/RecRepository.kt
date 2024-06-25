package com.xero.interview.bankrecmatchmaker.recmatcher.repositories

import com.xero.interview.bankrecmatchmaker.recmatcher.model.MatchItem

/**
 * A repository for getting all data to be used in the viewmodel, such as reconciliation items and the target match value.
 */
interface RecRepository {

    /**
     * Return the target match value for the screen.
     */
    fun getMatchTargetValue(): Float

    /**
     * Returns all the reconciliation items - could be from anywhere, such as a database or a server.
     */
    fun getMatchItems(): List<MatchItem>
}