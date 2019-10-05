package com.example.myapplication.modules

import android.content.res.Resources
import androidx.annotation.RawRes
import com.example.myapplication.R
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockEngineConfig
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestData
import io.ktor.http.*
import io.ktor.http.HttpMethod.Companion.Delete
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.http.HttpMethod.Companion.Post
import io.ktor.http.HttpMethod.Companion.Put
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

object MockModule : KodeinModuleProvider {

    override fun provideModule(): Kodein.Builder.() -> Unit = {
        bind<HttpClient>(overrides = true) with singleton {
            HttpClient(MockEngine) {
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
                engine {
                    addHandler {
                        handleMockEngineRequest2(it, instance())
                    }
                }
            }
        }
    }

    private fun MockEngineConfig.handleMockEngineRequest(resources: Resources) = listOf(
        Triple("tournament", R.raw.tournament33, R.raw.tournaments),
        Triple("match", R.raw.match65, R.raw.matches),
        Triple("registration", R.raw.registration97, R.raw.registrations),
        Triple("game", R.raw.game129, R.raw.games)
    ).forEach { (endpointName, singleResId, multipleResId) ->
        addHandlerFor(endpointName, singleResId, multipleResId, resources)
    }

    private fun MockEngineConfig.addHandlerFor(
        endpointName: String,
        @RawRes singleResId: Int,
        @RawRes multipleResId: Int,
        resources: Resources
    ) = addHandler { request ->
        when (endpointName) {
            request.url.fullPath -> when (request.method) {
                Get -> respondJsonFromRawResources(multipleResId, resources)
                else -> buildMockEngineError(request)
            }
            in request.url.fullPath -> when (request.method) {
                Get, Post, Put -> respondJsonFromRawResources(singleResId, resources)
                Delete -> respond("", HttpStatusCode.NoContent)
                else -> buildMockEngineError(request)
            }
            else -> buildMockEngineError(request)
        }
    }

    private fun respondJsonFromRawResources(@RawRes resId: Int, resources: Resources) = respond(
        resources.openRawResource(resId).bufferedReader().use { it.readText() },
        headers = headersOf(HttpHeaders.ContentType to listOf(ContentType.Application.Json.toString()))
    )

    private fun buildMockEngineError(request: HttpRequestData): Nothing =
        error("$request cannot be handled by the mock engine")

    private fun handleMockEngineRequest2(request: HttpRequestData, res: Resources) = when {
        "tournament" == request.url.fullPath -> when (request.method) {
            Get -> respondJsonFromRawResources(R.raw.tournament33, res)
            else -> buildMockEngineError(request)
        }
        "tournament" in request.url.fullPath -> when (request.method) {
            Get, Post, Put -> respondJsonFromRawResources(R.raw.tournaments, res)
            Delete -> respond("", HttpStatusCode.NoContent)
            else -> buildMockEngineError(request)
        }
        "game" == request.url.fullPath -> when (request.method) {
            Get -> respondJsonFromRawResources(R.raw.game129, res)
            else -> buildMockEngineError(request)
        }
        "game" in request.url.fullPath -> when (request.method) {
            Get, Post, Put -> respondJsonFromRawResources(R.raw.games, res)
            Delete -> respond("", HttpStatusCode.NoContent)
            else -> buildMockEngineError(request)
        }
        "match" == request.url.fullPath -> when (request.method) {
            Get -> respondJsonFromRawResources(R.raw.match65, res)
            else -> buildMockEngineError(request)
        }
        "match" in request.url.fullPath -> when (request.method) {
            Get, Post, Put -> respondJsonFromRawResources(R.raw.matches, res)
            Delete -> respond("", HttpStatusCode.NoContent)
            else -> buildMockEngineError(request)
        }
        "registration" == request.url.fullPath -> when (request.method) {
            Get -> respondJsonFromRawResources(R.raw.registration97, res)
            else -> buildMockEngineError(request)
        }
        "registration" in request.url.fullPath -> when (request.method) {
            Get, Post, Put -> respondJsonFromRawResources(R.raw.registration97, res)
            Delete -> respond("", HttpStatusCode.NoContent)
            else -> buildMockEngineError(request)
        }
        else -> buildMockEngineError(request)
    }
}

