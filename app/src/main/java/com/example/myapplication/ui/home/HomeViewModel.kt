package com.example.myapplication.ui.home

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.entities.MatchEntity
import com.example.myapplication.usecases.game.CreateGameUseCase
import com.example.myapplication.usecases.game.GetGamesByMode
import com.example.myapplication.usecases.game.GetGamesContainingName
import com.example.myapplication.usecases.match.CreateMatchUseCase
import com.example.myapplication.usecases.match.GetAllAvailableMatchesUseCase
import com.example.myapplication.usecases.match.GetAllMatchesByUserUseCase
import com.example.myapplication.usecases.match.GetMatchesByTournament
import com.example.myapplication.usecases.mode.CreateGameModeUseCase
import com.example.myapplication.usecases.registration.CreateRegistrationUseCase
import com.example.myapplication.usecases.registration.GetAllRegistrationsByMatch
import com.example.myapplication.usecases.registration.GetRegistrationsByTournamentUseCase
import com.example.myapplication.usecases.registration.GetRegistrationsByUser
import com.example.myapplication.usecases.tournament.*
import com.example.myapplication.usecases.user.CreateUserUseCase
import com.example.myapplication.usecases.user.GetAccountVerificationStatusUseCase
import com.example.myapplication.usecases.user.GetSubscribedAccountUseCase
import com.example.myapplication.usecases.user.GetUserInfoUseCase
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import kotlinx.android.synthetic.main.item_match.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class HomeViewModel(
    private val getGamesByMode: GetGamesByMode,
    private val getGamesContainingName: GetGamesContainingName,
    private val getAllAvailableMatches: GetAllAvailableMatchesUseCase,
    private val getAllMatchesByUser: GetAllMatchesByUserUseCase,
    private val getUserInfo: GetUserInfoUseCase,
    private val getCreatedTournamentsByAdmin: GetCreatedTournamentsByAdmin,
    private val getShowcaseTournaments: GetShowCaseTournaments,
    private val getRegistrationsByTournament: GetRegistrationsByTournamentUseCase,
    private val getTournamentsByGame: GetTournamentsByGame,
    private val getTournamentsByMode: GetTournamentsByMode,
    private val getTournamentsContainingTitle: GetTournamentsContainingTitle,
    private val getRegistrationsByMatch: GetAllRegistrationsByMatch,
    private val getRegistrationsByUser: GetRegistrationsByUser,
    private val getMatchesByTournament: GetMatchesByTournament,
    private val isAccountVerified: GetAccountVerificationStatusUseCase,
    private val isSubscribedAccount: GetSubscribedAccountUseCase,
    private val createUser: CreateUserUseCase,
    private val createGameMode: CreateGameModeUseCase,
    private val createGame: CreateGameUseCase,
    private val createTournament: CreateTournamentUseCase,
    private val createMatch: CreateMatchUseCase,
    private val createRegistration: CreateRegistrationUseCase
) : ViewModel() {

    private val homeViewAdapter = FlexibleAdapter<Model>(emptyList())

    val adapter
        get() = homeViewAdapter as RecyclerView.Adapter<*>

    data class Model(val matchEntity: MatchEntity, val registeredPlayer: Int) :
        AbstractFlexibleItem<Model.ViewHolder>() {
        override fun bindViewHolder(
            adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>,
            holder: ViewHolder,
            position: Int,
            payloads: MutableList<Any>
        ) {
            holder.render(matchEntity, registeredPlayer)

        }

        override fun createViewHolder(
            view: View,
            adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
        ) = ViewHolder(view, adapter)

        override fun getLayoutRes() = R.layout.item_match

        class ViewHolder(val view: View, val adapter: FlexibleAdapter<*>) : FlexibleViewHolder(
            view, adapter
        ) {
            @SuppressLint("SetTextI18n")
            fun render(data: MatchEntity, registeredPlayer: Int) {
                view.game_image.setImageResource(R.drawable.doggo)
                view.game_icon.setImageResource(R.drawable.wow)
                view.tournament_name.text = data.tournament.title

                val matchInfo = "${data.matchDateTime}".split("T")
                view.match_date.text = matchInfo[0]
                view.match_time.text = matchInfo[1]

                view.match_players.text = "$registeredPlayer|2"
                //view.match_players.text = Resources.getSystem().getString(R.string.players_number_text, registeredPlayer, 2)
                view.game_name.text = data.tournament.game.name
            }

        }
    }

    @ExperimentalCoroutinesApi
    fun getAllAvailableMatches() =
        getAllAvailableMatches.buildAction()
            .map {
                Model(it.first, it.second) }
            .onEach {
                homeViewAdapter.addItem(it)
            }
            .launchIn(viewModelScope)

    /**fun getShowcaseTournaments() =
        getShowcaseTournaments.buildAction()
            .map {

            }
            .onEach {
                homeViewAdapter.addItem(it)
            }
            .launchIn(viewModelScope)*/

    /** @ExperimentalCoroutinesApi
    fun getAllAvailableMatches() =
    getAllAvailableMatches.buildAction()
    .map {
    Model(it.first, it.second) }
    .onEach {
    homeViewAdapter.addItem(it)
    }
    .launchIn(viewModelScope)*/

    /**@InternalCoroutinesApi
    fun getGamesByMode() = viewModelScope.launch {
    getGamesByMode.buildAction(DummyClasses.game.availableModes[0])
    }

    fun getGamesContainingName() = viewModelScope.launch {
    getGamesContainingName.buildAction(DummyClasses.game.name)
    }


    @FlowPreview
    fun getMatchesByUser() = viewModelScope.launch {
    getAllMatchesByUser.buildAction()
    }

    @FlowPreview
    fun getMatchesByTournament() = viewModelScope.launch {
    getMatchesByTournament.buildAction(DummyClasses.tournament)
    }

    @FlowPreview
    fun getRegistrationsByMatch() = viewModelScope.launch {
    getRegistrationsByMatch.buildAction(DummyClasses.match)
    }

    fun getRegistrationByTournament() = viewModelScope.launch {
    getRegistrationsByTournament.buildAction(DummyClasses.tournament)
    }

    @FlowPreview
    fun getRegistrationsByUser() = viewModelScope.launch {
    getRegistrationsByUser.buildAction(DummyClasses.user)
    }

    @FlowPreview
    fun getTournamentsByAdmin() = viewModelScope.launch {
    getCreatedTournamentsByAdmin.buildAction()
    }

    fun getShowcaseTournaments() = viewModelScope.launch {
    getShowcaseTournaments.buildAction()
    }

    @FlowPreview
    fun getTournamentsByGame() = viewModelScope.launch {
    getTournamentsByGame.buildAction(DummyClasses.game)
    }

    fun getTournamentsByMode() = viewModelScope.launch {
    getTournamentsByMode.buildAction(DummyClasses.game.availableModes[0])
    }

    @FlowPreview
    fun getTournamentsContainingTitle() = viewModelScope.launch {
    getTournamentsContainingTitle.buildAction(DummyClasses.tournament.title)
    }

    fun getUserInformation() = viewModelScope.launch {
    getUserInfo.buildAction()
    }

    fun isAccountVerified() = viewModelScope.launch {
    isAccountVerified.buildAction()
    }

    fun isSubscribedAccount() = viewModelScope.launch {
    isSubscribedAccount.buildAction()
    }

    fun createGameMode() = viewModelScope.launch {
    createGameMode.buildAction("S&D")
    }

    fun createGame() = viewModelScope.launch {
    createGame.buildAction(
    DummyClasses.game.name,
    DummyClasses.game.availableModes,
    "image",
    "icon"
    )
    }

    fun createUser() = viewModelScope.launch {
    createUser.buildAction(
    DummyClasses.user.email,
    "PWD",
    DummyClasses.user.nickname,
    DummyClasses.user.image
    )
    }

    fun createTournament() = viewModelScope.launch {
    createTournament.buildAction(
    DummyClasses.tournament.playersNumber,
    DummyClasses.tournament.title,
    DummyClasses.tournament.tournamentDescription,
    DummyClasses.tournament.tournamentMode,
    DummyClasses.tournament.admin,
    DummyClasses.game
    )
    }

    fun createMatch() = viewModelScope.launch {
    createMatch.buildAction(
    DummyClasses.match.matchDateTime,
    DummyClasses.match.playersCount,
    DummyClasses.match.isRegistrationPossible,
    DummyClasses.tournament
    )
    }

    fun createRegistration() = viewModelScope.launch {
    createRegistration.buildAction(DummyClasses.user, DummyClasses.match)
    }*/

}