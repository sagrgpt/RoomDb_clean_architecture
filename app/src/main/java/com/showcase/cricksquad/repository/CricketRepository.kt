package com.showcase.cricksquad.repository

import com.showcase.cricksquad.*
import com.showcase.cricksquad.repository.dataSource.CricketCache
import com.showcase.cricksquad.repository.dataSource.CricketRemote
import com.showcase.cricksquad.repository.model.BattingEntity
import com.showcase.cricksquad.repository.model.BowlingEntity
import com.showcase.cricksquad.repository.model.PlayerEntity
import com.showcase.cricksquad.repository.model.TeamEntity
import io.reactivex.Observable
import io.reactivex.Single

class CricketRepository(
    private val remote: CricketRemote,
    private val cache: CricketCache
) {
    fun getAllTeams(): Observable<List<Team>> {
        return getTeamListFromRemote()
            .publish{network ->
                Observable.merge(
                    network,
                    getTeamListFromCache().takeUntil(network)
                )
            }.distinctUntilChanged()
    }

    fun getProfile(playerId: Long): Single<PlayerProfile> {
        return cache.getPlayer(playerId)
            .map { it.toProfile() }
    }

    private fun getTeamListFromCache(): Observable<List<Team>> {
        return cache.getAllTeams()
            .map { it.toTeam() }
            .toList()
            .toObservable()
            .filter { it.isNotEmpty() }
    }

    private fun getTeamListFromRemote(): Observable<List<Team>> {
        return remote.getSquadList()
            .flatMapObservable { Observable.fromIterable(it) }
            .doOnNext { cache.addTeam(it) }
            .map { it.toTeam() }
            .toList()
            .toObservable()
    }

    private fun TeamEntity.toTeam(): Team {
        return Team(
            teamId,
            nameFull,
            nameShort,
            players.toPlayers()
        )
    }

    private fun List<PlayerEntity>.toPlayers(): List<Player> {
        val playersList = mutableListOf<Player>()
        forEach { playersList.add(it.toPlayer()) }
        return playersList
    }

    private fun PlayerEntity.toPlayer(): Player {
        return Player(id, teamId, nameFull, position, isCaptain, isKeeper)
    }

    private fun PlayerEntity.toProfile(): PlayerProfile {
        return PlayerProfile(
            toPlayer(),
            batting.toBattingStats(),
            bowling.toBowlingStats()
        )
    }

    private fun BattingEntity.toBattingStats(): BattingStats {
        return BattingStats(average, runs, strikeRate, style)
    }

    private fun BowlingEntity.toBowlingStats(): BowlingStats {
        return BowlingStats(average, economyRate, style, wickets)
    }
}
