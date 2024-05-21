package com.example.upstox_assignment.di

import com.example.upstox_assignment.domain.repository.UserRepository
import com.example.upstox_assignment.domain.useCase.GetUserHoldingsUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideGetUserHoldingsUseCase(userRepository: UserRepository): GetUserHoldingsUseCase =
        GetUserHoldingsUseCase(userRepository)
}
