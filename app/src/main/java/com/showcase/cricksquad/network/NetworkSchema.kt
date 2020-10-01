package com.showcase.cricksquad.network

import com.google.gson.annotations.SerializedName

data class SquadSchema(
    @SerializedName("Teams")
    val teams: Map<Long ,TeamSchema> = hashMapOf()
)

data class TeamSchema(
    @SerializedName("Name_Full")
    val nameFull: String = "",
    @SerializedName("Name_Short")
    val nameShort: String = "",
    @SerializedName("Players")
    val players: Map<Long, PlayerSchema> = hashMapOf()
)

data class PlayerSchema(
    @SerializedName("Name_Full")
    val nameFull: String = "",
    @SerializedName("Position")
    val position: String = "",
    @SerializedName("Iscaptain")
    val isCaptain: Boolean = false,
    @SerializedName("Iskeeper")
    val isKeeper: Boolean = false,
    @SerializedName("Batting")
    val batting: BattingSchema = BattingSchema(),
    @SerializedName("Bowling")
    val bowling: BowlingSchema = BowlingSchema()
)

data class BattingSchema(
    @SerializedName("Average")
    val average: String = "",
    @SerializedName("Runs")
    val runs: String = "",
    @SerializedName("Strikerate")
    val strikerate: Float = 0f,
    @SerializedName("Style")
    val style: String = ""
)

data class BowlingSchema(
    @SerializedName("Average")
    val average: String = "",
    @SerializedName("Economyrate")
    val economyrate: Float = 0f,
    @SerializedName("Style")
    val style: String = "",
    @SerializedName("Wickets")
    val wickets: String = ""
)