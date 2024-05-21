package com.example.upstox_assignment.di

import com.example.upstox_assignment.data.api.ApiService
import com.example.upstox_assignment.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    fun provideUserRepository(): UserRepository = UserRepository()
}
