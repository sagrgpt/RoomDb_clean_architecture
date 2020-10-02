package com.showcase.cricksquad.database.room

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTeam(team: TeamDbo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlayer(player: PlayerDbo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllPlayer(playerList: List<PlayerDbo>)

    @Transaction
    @Query("SELECT * FROM teams_table WHERE team_id = :id")
    fun getTeamWithPlayers(id: Long): Single<TeamWithPlayers>

    @Transaction
    @Query("SELECT * FROM teams_table")
    fun getAllTeamsWithPlayers(): Single<List<TeamWithPlayers>>

    @Query("SELECT * FROM players_table WHERE player_id = :playerId")
    fun getPlayer(playerId: Long): Single<PlayerDbo>
}