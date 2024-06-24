package com.sborges.newsapp.domain.usecase.news

import androidx.paging.PagingData
import com.sborges.newsapp.domain.model.Article
import com.sborges.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}