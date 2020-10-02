package com.showcase.cricksquad.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.showcase.cricksquad.repository.CricketRepository
import com.showcase.cricksquad.schedulers.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class SquadViewModel(
    private val scheduler: SchedulerProvider,
    private val repository: CricketRepository
) : ViewModel() {

    private val disposables = CompositeDisposable()

    fun btnClicked() {
        disposables.add(
        repository.getAllTeams()
            .subscribeOn(scheduler.io)
            .observeOn(scheduler.ui)
            .subscribe { Log.v("MainActivity", "$it") }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}

class SquadVmFactory(
    private val scheduler: SchedulerProvider,
    private val repository: CricketRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SquadViewModel(scheduler, repository) as T
    }
}