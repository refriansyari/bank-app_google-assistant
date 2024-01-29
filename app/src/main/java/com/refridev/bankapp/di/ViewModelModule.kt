package com.refridev.bankapp.di

import com.refridev.bankapp.base.GenericViewModelFactory
import com.refridev.bankapp.ui.recipient.RecipientListRepository
import com.refridev.bankapp.ui.recipient.RecipientListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ViewModelModule {

    @Provides
    @ActivityScoped
    fun provideRecipientListViewModel(
        recipientListRepository: RecipientListRepository
    ): RecipientListViewModel {
        return GenericViewModelFactory(RecipientListViewModel(recipientListRepository)).create(
            RecipientListViewModel::class.java
        )
    }
}