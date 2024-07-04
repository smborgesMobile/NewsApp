package com.sborges.newsapp.domain.usecase.news

import com.sborges.newsapp.data.local.NewsDao
import com.sborges.newsapp.domain.model.Article

class DeleteArticle(
    private val dao: NewsDao
) {

    suspend operator fun invoke(article: Article) {
        dao.delete(article)
    }
}