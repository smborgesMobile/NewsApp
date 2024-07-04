package com.sborges.newsapp.domain.usecase.news

import com.sborges.newsapp.domain.model.Article
import com.sborges.newsapp.domain.repository.NewsRepository

class SelectArticleUseCase(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectArticle(url)
    }
}