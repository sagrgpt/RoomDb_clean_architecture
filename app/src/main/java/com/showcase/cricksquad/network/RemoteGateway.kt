package com.showcase.cricksquad.network

import com.showcase.cricksquad.NetworkGateway
import com.showcase.cricksquad.repository.dataSource.CricketRemote

class RemoteGateway(
    private val cricketService: CricketService
) : NetworkGateway {

    override fun getCricketRemote(): CricketRemote {
        return CricketRemoteImpl(cricketService)
    }

}