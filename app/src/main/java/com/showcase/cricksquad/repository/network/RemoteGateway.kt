package com.showcase.cricksquad.repository.network

import com.showcase.cricksquad.repository.NetworkGateway
import com.showcase.cricksquad.repository.dataSource.CricketRemote

class RemoteGateway(
    private val cricketService: CricketService
) : NetworkGateway {

    override fun getCricketRemote(): CricketRemote {
        return CricketRemoteImpl(cricketService)
    }

}