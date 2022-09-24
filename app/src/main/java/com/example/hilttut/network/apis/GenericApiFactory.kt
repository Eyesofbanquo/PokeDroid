package com.example.hilttut.network.apis

import com.example.hilttut.network.UrlService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ActivityComponent::class)
object GenericApiFactory {

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun makeGenericService(): UrlService {
        return getInstance()
            .create(UrlService::class.java)
    }
}