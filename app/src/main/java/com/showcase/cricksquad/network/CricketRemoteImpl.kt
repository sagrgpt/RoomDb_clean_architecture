package com.showcase.cricksquad.network

import com.showcase.cricksquad.repository.dataSource.CricketRemote
import com.showcase.cricksquad.repository.model.BattingEntity
import com.showcase.cricksquad.repository.model.BowlingEntity
import com.showcase.cricksquad.repository.model.PlayerEntity
import com.showcase.cricksquad.repository.model.TeamEntity
import io.reactivex.Single

/**
 * This class converts the json values from server to network entity
 * @param service: The service needed for api communication
 * @see [CricketService]
 */
class CricketRemoteImpl(
    private val service: CricketService
) : CricketRemote {

    override fun getSquadList(): Single<List<TeamEntity>> {
        return service.getSquad()
            .map { it.toTeamList() }
    }

    private fun SquadSchema.toTeamList(): List<TeamEntity> {
        val list: MutableList<TeamEntity> = mutableListOf()
        for (id in teams.keys)
            teams[id]
                ?.toTeamEntity(id)
                ?.also { list.add(it) }
        return list
    }

    private fun TeamSchema.toTeamEntity(id: Long): TeamEntity {
        val playerList: MutableList<PlayerEntity> = mutableListOf()
        for (playerId in players.keys)
            players[playerId]
                ?.toPlayerEntity(playerId, id)
                ?.also { playerList.add(it) }
        return TeamEntity(
            id,
            nameFull,
            nameShort,
            playerList
        )
    }

    private fun PlayerSchema.toPlayerEntity(id: Long, teamId: Long): PlayerEntity {
        return PlayerEntity(
            id,
            teamId,
            nameFull,
            position,
            isCaptain,
            isKeeper,
            batting.toEntity(),
            bowling.toEntity()
        )
    }

    private fun BattingSchema.toEntity(): BattingEntity {
        return BattingEntity(
            average.toFloatOrNull() ?: 0f,
            runs.toIntOrNull() ?: 0,
            strikerate.toFloatOrNull() ?: 0f,
            style
        )
    }

    private fun BowlingSchema.toEntity(): BowlingEntity {
        return BowlingEntity(
            average.toFloatOrNull() ?: 0f,
            economyrate.toFloatOrNull() ?: 0f,
            style,
            wickets.toIntOrNull() ?: 0
        )
    }
}
