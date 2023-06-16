package com.techlads.composetv.di

import com.techlads.composetv.features.data.repository.MoviesRepositoryImpl
import com.techlads.composetv.features.domain.datasource.network.MoviesApi
import com.techlads.composetv.features.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(api: MoviesApi): MoviesRepository = MoviesRepositoryImpl(api)
}