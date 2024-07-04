package com.sborges.newsapp.domain.usecase.news

import com.sborges.newsapp.data.local.NewsDao
import com.sborges.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val dao: NewsDao
) {

    operator fun invoke(): Flow<List<Article>> {
        return dao.getArticles()
    }
}