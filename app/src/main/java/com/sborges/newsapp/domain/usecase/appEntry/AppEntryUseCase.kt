package com.sborges.newsapp.domain.usecase.appEntry

data class AppEntryUseCase(
    val readAppEntryUseCase: ReadAppEntryUseCase,
    val saveAppEntryUseCase: SaveAppEntryUseCase
)
