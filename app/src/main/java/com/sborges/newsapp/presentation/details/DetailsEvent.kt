package com.sborges.newsapp.presentation.details

sealed class DetailsEvent {

    data object SaveArticleEvent : DetailsEvent()
}