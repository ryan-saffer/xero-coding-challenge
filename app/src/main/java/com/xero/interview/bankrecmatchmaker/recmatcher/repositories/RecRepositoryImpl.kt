package com.xero.interview.bankrecmatchmaker.recmatcher.repositories

import com.xero.interview.bankrecmatchmaker.recmatcher.model.MatchItem

class RecRepositoryImpl : RecRepository {

    companion object {
        const val DEFAULT_MATCH_VALUE = 10_000f
    }

    override fun getMatchTargetValue() = DEFAULT_MATCH_VALUE

    override fun getMatchItems() = listOf(
        MatchItem(
            "1",
            "City Limousines",
            "30 Aug",
            249.00f,
            "Sales Invoice"
        ),
        MatchItem(
            "2",
            "Ridgeway University",
            "12 Sep",
            618.50f,
            "Sales Invoice"
        ),
        MatchItem(
            "3",
            "Cube Land",
            "22 Sep",
            495.00f,
            "Sales Invoice"
        ),
        MatchItem(
            "4",
            "Bayside Club",
            "23 Sep",
            234.00f,
            "Sales Invoice"
        ),
        MatchItem(
            "5",
            "SMART Agency",
            "12 Sep",
            250f,
            "Sales Invoice"
        ),
        MatchItem(
            "6",
            "PowerDirect",
            "11 Sep",
            108.60f,
            "Sales Invoice"
        ),
        MatchItem(
            "7",
            "PC Complete",
            "17 Sep",
            216.99f,
            "Sales Invoice"
        ),
        MatchItem(
            "8",
            "Truxton Properties",
            "17 Sep",
            181.25f,
            "Sales Invoice"
        ),
        MatchItem(
            "9",
            "MCO Cleaning Services",
            "17 Sep",
            170.50f,
            "Sales Invoice"
        ),
        MatchItem(
            "10",
            "Gateway Motors",
            "18 Sep",
            411.35f,
            "Sales Invoice"
        ),
    )

}