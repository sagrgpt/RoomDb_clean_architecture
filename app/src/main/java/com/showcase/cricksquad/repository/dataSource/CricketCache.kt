package com.showcase.cricksquad.repository.dataSource

import com.showcase.cricksquad.repository.PlayerEntity
import com.showcase.cricksquad.repository.TeamEntity
import io.reactivex.Observable
import io.reactivex.Single

interface CricketCache {
    fun addTeam(team: TeamEntity)
    fun getTeams(teamId: Long): Single<TeamEntity>
    fun getAllTeams(): Observable<TeamEntity>
    fun getPlayer(playerId: Long): Single<PlayerEntity>
}