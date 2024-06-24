package com.sborges.newsapp.domain.repository

import androidx.paging.PagingData
import com.sborges.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    fun searchNews(
        searchQuery: String,
        sources: List<String>
    ): Flow<PagingData<Article>>
}