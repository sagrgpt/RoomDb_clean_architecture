package com.showcase.cricksquad

import com.showcase.cricksquad.repository.dataSource.CricketRemote

/**
 * This Interface is the single point of entrance to the
 * network module. It exposes all the remotes that
 * the repository layer expects the network layer to host.
 */
interface NetworkGateway {
    fun getCricketRemote(): CricketRemote
}