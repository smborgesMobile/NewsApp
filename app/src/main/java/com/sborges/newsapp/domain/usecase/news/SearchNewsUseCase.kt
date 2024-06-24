package com.sborges.newsapp.domain.usecase.news

import androidx.paging.PagingData
import com.sborges.newsapp.domain.model.Article
import com.sborges.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNewsUseCase(
    private val repository: NewsRepository
) {

    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return repository.searchNews(sources = sources, searchQuery = searchQuery)
    }
}