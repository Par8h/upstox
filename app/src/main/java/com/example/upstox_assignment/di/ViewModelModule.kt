package com.example.upstox_assignment.di

import com.example.upstox_assignment.presentation.viewmodel.UserHoldingsViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.Binds
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserHoldingsViewModel::class)
    abstract fun bindUserHoldingsViewModel(viewModel: UserHoldingsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
