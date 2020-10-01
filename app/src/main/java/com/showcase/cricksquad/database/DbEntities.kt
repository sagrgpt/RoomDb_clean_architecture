package com.showcase.cricksquad.database

import androidx.room.*

@Entity(tableName = "teams_table")
data class Team (
    @PrimaryKey
    @ColumnInfo(name="team_id")
    val id: Long,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @ColumnInfo(name = "short_name")
    val shortName: String,
)

@Entity(tableName = "players_table")
data class Player(
    @PrimaryKey
    @ColumnInfo(name= "player_id")
    val id: Long,
    @ColumnInfo(name = "player_team_id")
    val teamId: Long,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    val position: String,
    @ColumnInfo(name = "is_captain")
    val isCaptain: Boolean,
    @ColumnInfo(name = "is_keeper")
    val isKeeper: Boolean,
    @Embedded val batting: BattingStats,
    @Embedded val bowling: BowlingStats
)

data class BattingStats(
    @ColumnInfo(name = "batting_average")
    val average: Float,
    val runs: Int,
    @ColumnInfo(name="strike_rate")
    val strikeRate: Float,
    @ColumnInfo(name="batting_style")
    val style: String,
)

data class BowlingStats(
    @ColumnInfo(name="bowling_avg")
    val average: Float,
    @ColumnInfo(name="economy_rate")
    val economyRate: Float,
    @ColumnInfo(name="bowling_style")
    val style: String,
    @ColumnInfo(name="wickets_taken")
    val wicketsTaken: Int
)

data class TeamWithPlayers(
    @Embedded val team: Team,
    @Relation(
        parentColumn = "team_id",
        entityColumn = "player_team_id"
    )
    val players: List<Player>
)