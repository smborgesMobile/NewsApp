package com.sborges.newsapp.domain.usecase.news

import com.sborges.newsapp.domain.model.Article
import com.sborges.newsapp.domain.repository.NewsRepository

class DeleteArticle(
    private val repository: NewsRepository
) {

    suspend operator fun invoke(article: Article) {
        repository.deleteArticle(article)
    }
}