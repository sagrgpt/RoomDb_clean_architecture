package com.showcase.cricksquad.repository

/**
 * An exception wrapper for network module
 */
data class NetworkException(
    val code: Int,
    override val message: String
) : Exception(message)