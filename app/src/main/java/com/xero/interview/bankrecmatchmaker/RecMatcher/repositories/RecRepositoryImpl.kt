package com.xero.interview.bankrecmatchmaker.RecMatcher.repositories

import com.xero.interview.bankrecmatchmaker.RecMatcher.model.MatchItem

class RecRepositoryImpl : RecRepository {
    override fun getMatchItems(): List<MatchItem> {
        return listOf(
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
}