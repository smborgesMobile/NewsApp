package com.sborges.newsapp.domain.usecase.news

import com.sborges.newsapp.domain.model.Article
import com.sborges.newsapp.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article) {
        newsRepository.upsertArticle(article)
    }
}