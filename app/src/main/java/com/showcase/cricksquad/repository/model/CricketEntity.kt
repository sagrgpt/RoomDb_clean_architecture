package com.showcase.cricksquad.repository.model

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
    val nameFull: String,
    val position: String,
    val isCaptain: Boolean,
    val isKeeper: Boolean,
    val batting: BattingEntity,
    val bowling: BowlingEntity
)

data class BattingEntity(
    val average: String,
    val runs: String,
    val strikeRate: Float,
    val style: String
)

data class BowlingEntity(
    val average: String,
    val economyRate: Float,
    val style: String,
    val wickets: String
)