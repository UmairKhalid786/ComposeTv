package com.techlads.auth.imp.di

import com.techlads.auth.AuthTokenProvider
import com.techlads.auth.UserSession
import com.techlads.auth.imp.DefaultUserSession
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {
    @Binds @Singleton
    abstract fun bindUserSession(impl: DefaultUserSession): UserSession
    @Binds @Singleton
    abstract fun bindTokenProvider(impl: DefaultUserSession): AuthTokenProvider
}