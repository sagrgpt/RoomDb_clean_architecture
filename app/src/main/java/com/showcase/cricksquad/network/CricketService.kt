package com.showcase.cricksquad.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 * A retrofit interface that exposes the Cricket APIs
 */
interface CricketService {

    /**
     * Get quotation for a multiple stocks.
     * @return Details of the teams in the match
     */
    @GET("sifeeds/cricket/live/json/sapk01222019186652.json")
    fun getSquad(): Single<SquadSchema>

}