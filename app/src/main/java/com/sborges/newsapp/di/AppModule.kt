package com.sborges.newsapp.di

import android.app.Application
import com.sborges.newsapp.data.manager.LocalUserManagerImpl
import com.sborges.newsapp.domain.manager.LocalUserManager
import com.sborges.newsapp.domain.usecase.appEntry.AppEntryUseCase
import com.sborges.newsapp.domain.usecase.appEntry.ReadAppEntryUseCase
import com.sborges.newsapp.domain.usecase.appEntry.SaveAppEntryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManager
    ): AppEntryUseCase = AppEntryUseCase(
        readAppEntryUseCase = ReadAppEntryUseCase(localUserManger),
        saveAppEntryUseCase = SaveAppEntryUseCase(localUserManger)
    )

}