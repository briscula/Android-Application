package com.example.myapplication.modules

import com.example.myapplication.splitters.GameSplitter
import com.example.myapplication.splitters.RegistrationSplitter
import com.example.myapplication.splitters.TournamentSplitter
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.singleton

object SplittersModule : KodeinModuleProvider {
    override fun provideModule(): Kodein.Builder.() -> Unit = {
        bind<GameSplitter>() with singleton { GameSplitter() }
        bind<RegistrationSplitter>() with singleton { RegistrationSplitter() }
        bind<TournamentSplitter>() with singleton { TournamentSplitter() }
    }
}