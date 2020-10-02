package com.showcase.cricksquad

data class Team(
    val teamId: Long,
    val fullName: String,
    val shortName: String,
    val players: List<Player> = listOf()
)

data class Player(
    val id: Long,
    val teamId: Long,
    val name: String,
    val position: String,
    val isCaptain: Boolean,
    val isKeeper: Boolean,
)

data class PlayerProfile(
    val details: Player,
    val battingStats: BattingStats,
    val bowlingStats: BowlingStats
)

data class BattingStats(
    val average: Float,
    val runs: Int,
    val strikeRate: Float,
    val style: String
)

data class BowlingStats(
    val average: Float,
    val economyRate: Float,
    val style: String,
    val wickets: Int
)