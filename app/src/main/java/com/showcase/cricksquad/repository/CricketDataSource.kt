package com.showcase.cricksquad.repository

import com.showcase.cricksquad.repository.model.TeamEntity
import io.reactivex.rxjava3.core.Single

interface CricketDataSource {

    /**
     * @return A list of teams in a match
     */
    fun getSquadList(): Single<List<TeamEntity>>

}