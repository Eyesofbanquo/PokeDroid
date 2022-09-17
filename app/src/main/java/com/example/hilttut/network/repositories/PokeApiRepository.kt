package com.example.hilttut.network.repositories

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import com.example.hilttut.model.Pokemon

interface PokeApiRepository: DefaultLifecycleObserver {
    fun getAllPokemon(): LiveData<List<Pokemon>>
    fun registerObserver(lifecycle: Lifecycle)
}