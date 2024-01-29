package com.refridev.bankapp.di

import com.refridev.bankapp.data.network.service.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNewsApiServices(): ApiServices {
        return ApiServices.invoke()
    }
}