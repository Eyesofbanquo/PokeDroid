package com.example.hilttut.network.repositories

import androidx.lifecycle.LiveData
import com.example.hilttut.model.Pokemon

interface GenericApiRepository {
    fun loadAllPokemon(urlString: String): LiveData<List<Pokemon>>
}