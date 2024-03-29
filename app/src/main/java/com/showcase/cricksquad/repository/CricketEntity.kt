package com.showcase.cricksquad.repository

/**
 * A data model that should be returned from any data source.
 */

data class TeamEntity(
    val teamId: Long,
    val nameFull: String,
    val nameShort: String,
    val players: List<PlayerEntity> = listOf()
)

data class PlayerEntity(
    val id: Long,
    val teamId: Long,
    val nameFull: String,
    val position: String,
    val isCaptain: Boolean,
    val isKeeper: Boolean,
    val batting: BattingEntity,
    val bowling: BowlingEntity
)

data class BattingEntity(
    val average: Float,
    val runs: Int,
    val strikeRate: Float,
    val style: String
)

data class BowlingEntity(
    val average: Float,
    val economyRate: Float,
    val style: String,
    val wickets: Int
)