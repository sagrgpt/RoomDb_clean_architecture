package com.showcase.cricksquad.ui

data class ProfileViewState(
    val id: Long,
    val teamId: Long,
    val name: String,
    val teamName: String,
    val battingAvg: Float,
    val runs: Int,
    val strikeRate: Float,
    val battingStyle: String,
    val bowlingAvg: Float,
    val economyRate: Float,
    val bowlingStyle: String,
    val wickets: Int
)