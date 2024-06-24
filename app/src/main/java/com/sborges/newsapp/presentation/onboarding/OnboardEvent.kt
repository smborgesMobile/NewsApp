package com.sborges.newsapp.presentation.onboarding

sealed class OnboardEvent {
    data object SaveAppEntry : OnboardEvent()
}