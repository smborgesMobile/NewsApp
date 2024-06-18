package com.sborges.newsapp.domain.usecase

data class AppEntryUseCase(
    val readAppEntryUseCase: ReadAppEntryUseCase,
    val saveAppEntryUseCase: SaveAppEntryUseCase
)
