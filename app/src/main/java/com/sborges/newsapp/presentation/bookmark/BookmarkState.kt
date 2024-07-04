package com.sborges.newsapp.presentation.bookmark

import com.sborges.newsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)