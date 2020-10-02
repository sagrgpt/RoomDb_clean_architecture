package com.showcase.cricksquad.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.showcase.cricksquad.R
import com.showcase.cricksquad.database.DatabaseFactory
import com.showcase.cricksquad.network.NetworkFactory
import com.showcase.cricksquad.repository.CricketRepository
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val networkGateway = NetworkFactory.createGateway()
        val localGateway = DatabaseFactory.getGateway(this)
        val repository = CricketRepository(
            networkGateway.getCricketRemote(),
            localGateway.getCricketCache()
        )
        button.setOnClickListener {
            repository.getAllTeams()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe { Log.v("MainActivity", "$it") }
        }
    }
}