package com.showcase.cricksquad.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.showcase.cricksquad.PlayerProfile
import com.showcase.cricksquad.Team
import com.showcase.cricksquad.repository.CricketRepository
import com.showcase.cricksquad.schedulers.SchedulerProvider
import com.showcase.cricksquad.ui.profile.ProfileViewState
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
    private val scheduler: SchedulerProvider,
    private val repository: CricketRepository
) : ViewModel() {

    private val disposables = CompositeDisposable()
    private val teamLiveData = MutableLiveData<List<Team>>()
    private val profileLiveData = MutableLiveData<ProfileViewState>()

    fun init() {
        disposables.add(
            repository.getAllTeams()
                .subscribeOn(scheduler.io)
                .observeOn(scheduler.ui)
                .subscribe { teamLiveData.value = it }
        )
    }

    fun viewProfile(id: Long) {
        disposables.add(
            repository.getProfile(id)
                .flatMap { player ->
                    repository.getTeam(player.details.teamId)
                        .map { player.toViewState(it.fullName) }
                }
                .subscribeOn(scheduler.io)
                .observeOn(scheduler.ui)
                .subscribe { t1 ->
                    profileLiveData.value = t1
                }
        )
    }

    fun team(): LiveData<List<Team>> = teamLiveData

    fun profile(): LiveData<ProfileViewState> = profileLiveData

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

    private fun PlayerProfile.toViewState(teamName: String): ProfileViewState {
        return ProfileViewState(
            details.id,
            details.teamId,
            details.name,
            teamName,
            battingStats.average,
            battingStats.runs,
            battingStats.strikeRate,
            battingStats.style,
            bowlingStats.average,
            bowlingStats.economyRate,
            bowlingStats.style,
            bowlingStats.wickets
        )
    }

}

class HomeVmFactory(
    private val scheduler: SchedulerProvider,
    private val repository: CricketRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(scheduler, repository) as T
    }
}