package com.showcase.cricksquad.repository.dataSource

import com.showcase.cricksquad.repository.model.TeamEntity
import io.reactivex.Single

interface CricketRemote {

    /**
     * @return A list of teams in a match
     */
    fun getSquadList(): Single<List<TeamEntity>>

}