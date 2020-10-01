package com.showcase.cricksquad.repository

/**
 * This Interface is the single point of entrance to the
 * network module. It exposes all the remotes that
 * the repository layer expects the network layer to host.
 */
interface DataSourceGateway {

    fun getCricketDataSource(): CricketDataSource

}