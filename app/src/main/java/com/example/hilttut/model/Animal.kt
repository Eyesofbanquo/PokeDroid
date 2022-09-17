package com.example.hilttut.model

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@AssistedFactory
interface AnimalUtilFactory {
    fun create(name: String, age: Int): Animal
}

class Animal @AssistedInject constructor (
    @Assisted val name: String,
    @Assisted val age: Int
    ) {

    init {
        Log.i("Animal", "$name is $age years old")
    }
}
