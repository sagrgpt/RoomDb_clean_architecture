package com.showcase.cricksquad.network

import com.showcase.cricksquad.repository.DataSourceGateway
import com.showcase.cricksquad.repository.CricketDataSource

class NetworkGateway(
    private val cricketService: CricketService
) : DataSourceGateway {

    override fun getCricketDataSource(): CricketDataSource {
        return CricketRemote(cricketService)
    }

}