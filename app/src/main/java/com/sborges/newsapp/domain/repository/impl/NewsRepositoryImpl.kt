package com.sborges.newsapp.domain.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sborges.newsapp.data.local.NewsDao
import com.sborges.newsapp.data.remote.NewsApi
import com.sborges.newsapp.data.remote.NewsPagingSource
import com.sborges.newsapp.data.remote.SearchNewsPagingSource
import com.sborges.newsapp.domain.model.Article
import com.sborges.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {

    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi,
                    sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    api = newsApi,
                    searchQuery = searchQuery,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun selectArticle(articleUrl: String): Article? {
        return newsDao.selectArticle(articleUrl)
    }

    override suspend fun upsertArticle(article: Article) {
        val articleWithNonNullAuthor = article.copy(author = article.author ?: "Unknown Author")
        newsDao.upsert(articleWithNonNullAuthor)
    }
}