package com.showcase.cricksquad.database.room

import androidx.room.*

@Entity(tableName = "teams_table")
data class TeamDbo (
    @PrimaryKey
    @ColumnInfo(name="team_id")
    val id: Long,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @ColumnInfo(name = "short_name")
    val shortName: String,
)

@Entity(tableName = "players_table")
data class PlayerDbo(
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
    @Embedded val batting: BattingStatsDbo,
    @Embedded val bowling: BowlingStatsDbo
)

data class BattingStatsDbo(
    @ColumnInfo(name = "batting_average")
    val average: Float,
    val runs: Int,
    @ColumnInfo(name="strike_rate")
    val strikeRate: Float,
    @ColumnInfo(name="batting_style")
    val style: String,
)

data class BowlingStatsDbo(
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
    @Embedded val team: TeamDbo,
    @Relation(
        parentColumn = "team_id",
        entityColumn = "player_team_id"
    )
    val players: List<PlayerDbo>
)