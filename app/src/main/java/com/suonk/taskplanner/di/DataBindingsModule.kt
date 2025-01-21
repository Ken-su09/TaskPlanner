package com.suonk.taskplanner.di

import com.suonk.taskplanner.model.repository.users_list.UsersListRepository
import com.suonk.taskplanner.model.repository.users_list.UsersListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindingsModule {

    @Binds
    @Singleton
    abstract fun provideUsersListRepository(impl: UsersListRepositoryImpl): UsersListRepository
}