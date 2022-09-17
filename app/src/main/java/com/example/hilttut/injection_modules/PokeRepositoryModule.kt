package com.example.hilttut.injection_modules

import com.example.hilttut.network.PokemonRepository
import com.example.hilttut.network.repositories.PokeApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Qualifier

@Qualifier
annotation class PokemonRepositoryQualifier

@Module
@InstallIn(ActivityComponent::class)
abstract class PokeRepositoryModule {
    @PokemonRepositoryQualifier
    @Binds
    abstract fun makePokemonRepository(pokemonRepository: PokemonRepository): PokeApiRepository
}