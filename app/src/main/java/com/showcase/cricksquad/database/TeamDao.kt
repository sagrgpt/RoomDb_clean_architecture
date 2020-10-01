package com.showcase.cricksquad.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTeam(team: Team): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPlayer(player: Player): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllPlayer(playerList: List<Player>): Completable

    @Transaction
    @Query("SELECT * FROM teams_table WHERE team_id = :id")
    fun getTeamWithPlayers(id: Long): Single<TeamWithPlayers>

    @Query("SELECT * FROM players_table WHERE player_id = :playerId")
    fun getPlayer(playerId: Long): Single<Player>
}