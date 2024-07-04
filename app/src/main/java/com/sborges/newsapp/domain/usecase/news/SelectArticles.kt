package com.sborges.newsapp.domain.usecase.news

import com.sborges.newsapp.domain.model.Article
import com.sborges.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val repository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>> {
        return repository.selectArticles()
    }
}