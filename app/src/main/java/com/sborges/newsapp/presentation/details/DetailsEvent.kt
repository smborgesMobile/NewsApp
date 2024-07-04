package com.sborges.newsapp.presentation.details

import com.sborges.newsapp.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()
    data object RemovingSideEffect : DetailsEvent()
}