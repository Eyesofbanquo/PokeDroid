package com.example.hilttut.network.apis

import com.example.hilttut.network.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object PokeApiFactory {
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