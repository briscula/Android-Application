package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.entities.MatchEntity
import com.example.myapplication.usecases.*
import com.example.myapplication.usecases.tournament.GetTournamentsByUser
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAllAvailableMatchesUseCase: GetAllAvailableMatchesUseCase,
    private val getAllMatchesByUserUseCase: GetAllMatchesByUserUseCase,
    private val getTournamentsByUser: GetTournamentsByUser
) : ViewModel() {

    data class MatchWithPlayersCountModel(val matchEntity: MatchEntity, val registeredPlayer: Int)

    private val _matches = MutableLiveData<List<MatchWithPlayersCountModel>>()
    val text: LiveData<List<MatchWithPlayersCountModel>> = _matches

    fun derp() {
        getAllAvailableMatchesUseCase.buildActionAsync(GetAllAvailableMatchesUseCase.Params(1)) {
            it.map { MatchWithPlayersCountModel(it.first, it.second) }
                .let { _matches.value = it }
        }
    }

    fun loadMatches() = viewModelScope.launch {
        getAllAvailableMatchesUseCase.buildAction()
            .map { MatchWithPlayersCountModel(it.first, it.second) }
            .let { _matches.value = it }
    }

    fun getMatchesByUser() = viewModelScope.launch {
        getAllMatchesByUserUseCase.buildAction()
    }

    fun getTsByUser() = viewModelScope.launch {
        getTournamentsByUser.buildAction()
    }

}