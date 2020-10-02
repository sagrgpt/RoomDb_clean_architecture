package com.showcase.cricksquad.repository

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.showcase.cricksquad.repository.dataSource.CricketCache
import com.showcase.cricksquad.repository.dataSource.CricketRemote
import com.showcase.cricksquad.repository.model.BattingEntity
import com.showcase.cricksquad.repository.model.BowlingEntity
import com.showcase.cricksquad.repository.model.PlayerEntity
import com.showcase.cricksquad.repository.model.TeamEntity
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CricketRepositoryTest {


    //region Static dependencies
    private val batting = BattingEntity(12.0f, 123, 3.5f, "LHB")
    private val bowling = BowlingEntity(12.0f, 10.2f, "RHB", 12)
    private val teamPlayer = PlayerEntity(101, 1, "Abc", "Forward", false, false, batting, bowling)
    private val captain = teamPlayer.copy(isCaptain = true)
    private val keeper = teamPlayer.copy(isKeeper = true)
    private val team = TeamEntity(1, "Big Team Super", "Bts", listOf(captain, keeper, keeper, teamPlayer, teamPlayer, teamPlayer))
    //endregion

    @Mock
    private lateinit var remote: CricketRemote
    @Mock
    private lateinit var cache: CricketCache
    private lateinit var repo: CricketRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repo = CricketRepository(remote, cache)
    }

    @Test
    fun fetchValuesFromRemoteAfterFetchingFromDb() {

        `when`(cache.getAllTeams())
            .thenReturn(Observable.just(team, team))

        `when`(remote.getSquadList())
            .thenReturn(Single.just(listOf(team, team)))

        repo.getAllTeams()
            .test()
            .assertNoErrors()
            .assertComplete()
            .assertValueCount(1)
            .assertValueAt(0){
                it[0].teamId == 1L
                it[0].players.size == 6
                it[0].players[0].isCaptain
            }

        verify(cache, times(2))
            .addTeam(team)
    }

    @Test
    fun fetchValuesFromServerWhenDbIsEmpty() {
        `when`(cache.getAllTeams())
            .thenReturn(Observable.never())

        `when`(remote.getSquadList())
            .thenReturn(Single.just(listOf(team, team)))

        repo.getAllTeams()
            .test()
            .assertNoErrors()
            .assertComplete()
            .assertValueCount(1)
            .assertValueAt(0){
                it[0].teamId == 1L
                it[0].players.size == 6
                it[0].players[0].isCaptain
            }

        verify(cache, times(2))
            .addTeam(team)
    }

    @Test
    fun fetchPlayerDetails() {
        `when`(cache.getPlayer(101))
            .thenReturn(Single.just(teamPlayer))

        repo.getProfile(101L)
            .test()
            .assertNoErrors()
            .assertComplete()
            .assertValue {
                it.details.id == 101L
            }
    }

}