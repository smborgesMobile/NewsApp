package com.sborges.newsapp.di

import android.app.Application
import androidx.room.Room
import com.sborges.newsapp.data.local.NewsDao
import com.sborges.newsapp.data.local.NewsDataBase
import com.sborges.newsapp.data.local.NewsTypeConverter
import com.sborges.newsapp.data.manager.LocalUserManagerImpl
import com.sborges.newsapp.data.manager.ManagerConstants.BASE_URL
import com.sborges.newsapp.data.manager.ManagerConstants.NEWS_DATA_BASE
import com.sborges.newsapp.data.remote.NewsApi
import com.sborges.newsapp.domain.manager.LocalUserManager
import com.sborges.newsapp.domain.repository.NewsRepository
import com.sborges.newsapp.domain.repository.impl.NewsRepositoryImpl
import com.sborges.newsapp.domain.usecase.appEntry.AppEntryUseCase
import com.sborges.newsapp.domain.usecase.appEntry.ReadAppEntryUseCase
import com.sborges.newsapp.domain.usecase.appEntry.SaveAppEntryUseCase
import com.sborges.newsapp.domain.usecase.news.DeleteArticle
import com.sborges.newsapp.domain.usecase.news.GetNews
import com.sborges.newsapp.domain.usecase.news.NewsUseCases
import com.sborges.newsapp.domain.usecase.news.SearchNewsUseCase
import com.sborges.newsapp.domain.usecase.news.SelectArticles
import com.sborges.newsapp.domain.usecase.news.UpsertArticle
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
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            selectArticles = SelectArticles(newsDao),
            searchNews = SearchNewsUseCase(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun searchNewsUseCase(
        newsRepository: NewsRepository
    ): SearchNewsUseCase {
        return SearchNewsUseCase(
            repository = newsRepository
        )
    }

    @Provides
    @Singleton
    fun provideNewsDataBase(
        application: Application
    ): NewsDataBase =
        Room.databaseBuilder(
            context = application,
            klass = NewsDataBase::class.java,
            name = NEWS_DATA_BASE
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDataBase: NewsDataBase
    ): NewsDao {
        return newsDataBase.newsDao
    }

}