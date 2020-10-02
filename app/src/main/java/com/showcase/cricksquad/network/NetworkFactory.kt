package com.showcase.cricksquad.network

import com.showcase.cricksquad.NetworkGateway
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A self-contained factory for Network module.
 * Use this factory to get a single point of entrance to the network layer.
 */
object NetworkFactory {

    private const val baseUrl = "https://cricket.yahoo.net/"

    fun createGateway(): NetworkGateway {
        return RemoteGateway(getRemoteService())
    }

    private fun getRemoteService(): CricketService {
        val retrofit = retrofit(
            okHttpClient(httpLoggingInterceptor()),
            gsonFactoryConverter()
        )

        return retrofit.create(CricketService::class.java)
    }

    private fun retrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }


    private fun okHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private fun gsonFactoryConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}