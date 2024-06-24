package com.sborges.newsapp.data.remote.dto

import com.sborges.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)