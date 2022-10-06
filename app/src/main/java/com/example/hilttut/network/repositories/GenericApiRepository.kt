package com.example.hilttut.network.repositories

import androidx.lifecycle.LiveData
import com.example.hilttut.model.Pokemon
import com.example.hilttut.network.responses.AllPokemonResponse

interface GenericApiRepository {
    fun loadAllPokemon(urlString: String): LiveData<AllPokemonResponse?>
}