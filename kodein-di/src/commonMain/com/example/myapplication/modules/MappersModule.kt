package com.example.myapplication.modules

import com.example.myapplication.mappers.*
import com.example.myapplication.mappers.entitieslinkmapper.*
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

object MappersModule : KodeinModuleProvider {

    override fun provideModule(): Kodein.Builder.() -> Unit = {
        bind<AccountStatusMapper>() with singleton { AccountStatusMapper() }
        bind<AccountSubscriptionMapper>() with singleton { AccountSubscriptionMapper() }
        bind<GameMapper>() with singleton { GameMapper() }
        bind<RegistrationMapper>() with singleton { RegistrationMapper(instance(), instance()) }
        bind<TournamentMapper>() with singleton { TournamentMapper(instance(), instance()) }
        bind<UserMapper>() with singleton { UserMapper() }
        bind<ModeMapper>() with singleton { ModeMapper() }

        bind<GameLinkMapper>() with singleton {
            GameLinkMapper(
                instance("serverProtocol"),
                instance("serverUrl"),
                instance("serverPort")
            )
        }
        bind<MatchLinkMapper>() with singleton {
            MatchLinkMapper(
                instance("serverProtocol"),
                instance("serverUrl"),
                instance("serverPort")
            )
        }
        bind<TournamentLinkMapper>() with singleton {
            TournamentLinkMapper(
                instance("serverProtocol"),
                instance("serverUrl"),
                instance("serverPort")
            )
        }
        bind<UserLinkMapper>() with singleton {
            UserLinkMapper(
                instance("serverProtocol"),
                instance("serverUrl"),
                instance("serverPort")
            )
        }
        bind<ModeLinkMapper>() with singleton {
            ModeLinkMapper(
                instance("serverProtocol"),
                instance("serverUrl"),
                instance("serverPort")
            )
        }

        bind<CurrentUserMapper>() with singleton { CurrentUserMapper() }

    }

}