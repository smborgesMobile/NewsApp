package com.sborges.newsapp.presentation.search

import androidx.paging.PagingData
import com.sborges.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)