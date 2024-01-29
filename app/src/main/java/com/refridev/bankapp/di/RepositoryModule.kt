package com.refridev.bankapp.di

import com.refridev.bankapp.data.network.datasource.recipient.RecipientListDataSource
import com.refridev.bankapp.ui.recipient.RecipientListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsListRepository(
        recipientListDataSource: RecipientListDataSource
    ): RecipientListRepository {
        return RecipientListRepository(recipientListDataSource)
    }
}