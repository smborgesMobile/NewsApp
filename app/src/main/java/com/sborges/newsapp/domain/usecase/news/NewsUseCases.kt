package com.sborges.newsapp.domain.usecase.news

data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNewsUseCase,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val selectArticles: SelectArticles
)
