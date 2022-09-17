package com.example.hilttut.network

import com.example.hilttut.model.Pokemon
import com.example.hilttut.network.RetrofitHelper.baseUrl
import com.example.hilttut.network.responses.AllPokemonResponse
import com.squareup.moshi.Json
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/* /pokemon-form/$index} */
data class PokemonFormResponse(@field:Json(name="pokemon") val pokemon: Pokemon,
                               @field:Json(name="sprites") val sprites: PokemonSprite)
data class PokemonSprite(@field:Json(name="front_default") val default: String)

interface PokemonService {
    @GET("/api/v2/pokemon")
    suspend fun getPokemon(): Response<AllPokemonResponse>
    @GET("/api/v2/pokemon-form/{id}")
    suspend fun getSpecificPokemonForm(@Path("id") id: String): Response<PokemonFormResponse>
}

@Module
@InstallIn(ActivityComponent::class)
object RetrofitHelper {
    const val baseUrl: String = "https://pokeapi.co/"

    private fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun makePokemonService(): PokemonService {
        return getInstance()
            .create(PokemonService::class.java)
    }
}

