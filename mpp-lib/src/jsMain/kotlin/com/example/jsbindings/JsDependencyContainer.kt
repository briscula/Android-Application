package com.example.jsbindings

import com.example.myapplication.datasource.TokenFactory
import com.example.myapplication.modules.JsUseCasesModule
import com.example.myapplication.modules.buildCommonKodein
import com.example.myapplication.modules.import
import com.example.myapplication.usecases.game.JsCreateGame
import com.example.myapplication.usecases.game.JsGetGamesByMode
import com.example.myapplication.usecases.game.JsGetGamesContainingName
import com.example.myapplication.usecases.match.JsCreateMatch
import com.example.myapplication.usecases.match.JsGetAllAvailableMatches
import com.example.myapplication.usecases.match.JsGetAllMatchesByUser
import com.example.myapplication.usecases.match.JsGetMatchesByTournament
import com.example.myapplication.usecases.mode.JsCreateGameMode
import com.example.myapplication.usecases.registration.JsCreateRegistration
import com.example.myapplication.usecases.registration.JsGetAllRegistrationsByMatch
import com.example.myapplication.usecases.registration.JsGetRegistrationsByTournament
import com.example.myapplication.usecases.tournament.*
import com.example.myapplication.usecases.user.JsCreateUser
import com.example.myapplication.usecases.user.JsGetAccountVerificationStatus
import com.example.myapplication.usecases.user.JsGetUserInfo
import com.example.myapplication.usecases.user.JsIsSubscribedAccount
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

@JsName("DependencyContainer")
class JsDependencyContainer(
    serverProtocol: String,
    serverUrl: String,
    serverPort: Int,
    factory: () -> String?
) : KodeinAware {

    override val kodein by Kodein.lazy {
        import(buildCommonKodein(serverProtocol, serverUrl, serverPort))
        import(JsUseCasesModule)
        bind<TokenFactory>() with singleton {
            object : TokenFactory {
                override val factory = factory
            }
        }
    }

    /* Game usecases */
    @JsName("createGame")
    val createGame by instance<JsCreateGame>()

    @JsName("getGamesByMode")
    val getGamesMode by instance<JsGetGamesByMode>()

    @JsName("getGamesContainingName")
    val getGamesContainingName by instance<JsGetGamesContainingName>()


    /* Match usecases */
    @JsName("createMatch")
    val createMatch by instance<JsCreateMatch>()

    @JsName("getAllAvailableMatches")
    val getAllAvailableMatches by instance<JsGetAllAvailableMatches>()

    @JsName("getAllMatchesByUser")
    val getAllMatchesByUser by instance<JsGetAllMatchesByUser>()

    @JsName("getAllMatchesByTournament")
    val getAllMatchesByTournament by instance<JsGetMatchesByTournament>()

    /* Mode usecases */
    @JsName("createGameModeUseCase")
    val createGameMode by instance<JsCreateGameMode>()

    /* Registration usecases */
    @JsName("createRegistration")
    val createRegistration by instance<JsCreateRegistration>()

    @JsName("getAllRegistrationsByMatch")
    val getAllRegistrationsByMatch by instance<JsGetAllRegistrationsByMatch>()

    @JsName("getAllRegistrationsByTournament")
    val getAllRegistrationsByTournament by instance<JsGetRegistrationsByTournament>()

    @JsName("getAllRegistrationsByUser")
    val getAllRegistrationsByUser by instance<JsGetAllMatchesByUser>()

    /* Tournament usecases */
    @JsName("createTournament")
    val createTournament by instance<JsCreateTournament>()

    @JsName("getCreatedTournamentsByAdmin")
    val getCreatedTournamentsByAdmin by instance<JsGetCreatedTournamentsByAdmin>()

    @JsName("getShowcaseTournaments")
    val getShowcaseTournaments by instance<JsGetShowcaseTournaments>()

    @JsName("getTournamentsByGame")
    val getTournamentsByGame by instance<JsGetTournamentsByGame>()

    @JsName("getTournamentsByMode")
    val getTournamentsByMode by instance<JsGetTournamentsByMode>()

    @JsName("getTournamentsContainingTitle")
    val getTournamentsContainingTitle by instance<JsGetTournamentsContainingTitle>()

    /* User usecases  */
    @JsName("createUser")
    val createUser by instance<JsCreateUser>()

    @JsName("getAccountVerificationStatusUseCase")
    val getAccountVerificationStatusUseCase by instance<JsGetAccountVerificationStatus>()

    @JsName("getUserInfo")
    val getUserInfo by instance<JsGetUserInfo>()

    @JsName("isSubscribed")
    val isSubscribed by instance<JsIsSubscribedAccount>()

}

// const container = new com.example.jsbindings.JsDependencyContainer("", "", "", {
//   w;lekfmelwrigw
// })
//  container.jsGetByModeUseCase.buildAction({ mode: "freeforall", page: 1 }) observable