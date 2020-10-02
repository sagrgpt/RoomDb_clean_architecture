package com.showcase.cricksquad.database

import android.util.Log
import com.showcase.cricksquad.database.room.*
import com.showcase.cricksquad.repository.dataSource.CricketCache
import com.showcase.cricksquad.repository.model.BattingEntity
import com.showcase.cricksquad.repository.model.BowlingEntity
import com.showcase.cricksquad.repository.model.PlayerEntity
import com.showcase.cricksquad.repository.model.TeamEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class CricketDb(
    private val dao: TeamDao
): CricketCache {

    override fun addTeam(team: TeamEntity) {
        dao.addTeam(team.toDbo())
        addPlayers(team.players)
    }

    override fun getTeams(teamId: Long): Single<TeamEntity> {
        return dao.getTeamWithPlayers(teamId)
            .map { it.toEntity() }
    }

    override fun getAllTeams(): Observable<TeamEntity> {
        return dao.getAllTeamsWithPlayers()
            .flatMapObservable { Observable.fromIterable(it) }
            .map { it.toEntity() }
    }

    override fun getPlayer(playerId: Long): Single<PlayerEntity> {
        return dao.getPlayer(playerId)
            .map { it.toEntity() }
    }

    private fun addPlayers(players: List<PlayerEntity>){
        val playerList = mutableListOf<PlayerDbo>()
        players.forEach { playerList.add(it.toDbo()) }
        dao.addAllPlayer(playerList)
    }


    private fun TeamEntity.toDbo(): TeamDbo {
        return TeamDbo(teamId, nameFull, nameShort)
    }

    private fun PlayerEntity.toDbo(): PlayerDbo {
        return PlayerDbo(
            id = id,
            teamId = teamId,
            fullName = nameFull,
            position = position,
            isCaptain = isCaptain,
            isKeeper = isKeeper,
            batting = batting.toDbo(),
            bowling = bowling.toDbo()
        )
    }

    private fun BattingEntity.toDbo(): BattingStatsDbo {
        return BattingStatsDbo(
            strikeRate = strikeRate,
            average = average,
            runs = runs,
            style = style
        )
    }

    private fun BowlingEntity.toDbo(): BowlingStatsDbo {
        return BowlingStatsDbo(
            average = average,
            economyRate = economyRate,
            style = style,
            wicketsTaken = wickets
        )
    }

    private fun TeamWithPlayers.toEntity(): TeamEntity {
        return TeamEntity(
            teamId = team.id,
            nameFull = team.fullName,
            nameShort = team.shortName,
            players = players.toEntity()
        )
    }

    private fun List<PlayerDbo>.toEntity(): List<PlayerEntity> {
        val playersList: MutableList<PlayerEntity> = mutableListOf()
        forEach { playersList.add(it.toEntity()) }
        return playersList
    }

    private fun PlayerDbo.toEntity(): PlayerEntity {
        return PlayerEntity(
            id = id,
            teamId = teamId,
            nameFull = fullName,
            position = position,
            isCaptain = isCaptain,
            isKeeper = isKeeper,
            batting = batting.toEntity(),
            bowling = bowling.toEntity()
        )
    }

    private fun BattingStatsDbo.toEntity(): BattingEntity {
        return BattingEntity(
            average,
            runs,
            strikeRate,
            style
        )
    }

    private fun BowlingStatsDbo.toEntity(): BowlingEntity {
        return BowlingEntity(
            average,
            economyRate,
            style,
            wicketsTaken
        )
    }
}
