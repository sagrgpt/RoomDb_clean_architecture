package com.showcase.cricksquad.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.showcase.cricksquad.R
import com.showcase.cricksquad.network.NetworkFactory
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NetworkFactory
            .createGateway()
            .getCricketDataSource()
            .getSquadList()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(
                { Log.v("MainActivity", "$it") },
                { Log.e("MainActivity", "${it.message}") }
            )
    }
}