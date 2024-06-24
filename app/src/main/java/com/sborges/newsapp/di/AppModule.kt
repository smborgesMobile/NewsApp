package com.sborges.newsapp.di

import android.app.Application
import com.sborges.newsapp.data.manager.LocalUserManagerImpl
import com.sborges.newsapp.data.manager.ManagerConstants.BASE_URL
import com.sborges.newsapp.data.remote.NewsApi
import com.sborges.newsapp.domain.manager.LocalUserManager
import com.sborges.newsapp.domain.repository.NewsRepository
import com.sborges.newsapp.domain.repository.impl.NewsRepositoryImpl
import com.sborges.newsapp.domain.usecase.appEntry.AppEntryUseCase
import com.sborges.newsapp.domain.usecase.appEntry.ReadAppEntryUseCase
import com.sborges.newsapp.domain.usecase.appEntry.SaveAppEntryUseCase
import com.sborges.newsapp.domain.usecase.news.GetNews
import com.sborges.newsapp.domain.usecase.news.NewsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository)
        )
    }

}