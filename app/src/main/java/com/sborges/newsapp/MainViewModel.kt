package com.sborges.newsapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sborges.newsapp.domain.usecase.appEntry.AppEntryUseCase
import com.sborges.newsapp.presentation.navGraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCase: AppEntryUseCase
) : ViewModel() {

    var splashCondition by mutableStateOf(true)

    var startDestination by mutableStateOf(Route.AppStartNavigation.router)
        private set

    init {
        viewModelScope.launch {
            appEntryUseCase.readAppEntryUseCase().onEach { shouldStartFromHomeScreen ->
                startDestination = if (shouldStartFromHomeScreen) {
                    Route.NewsNavigation.router
                } else {
                    Route.AppStartNavigation.router
                }
                delay(300)
                splashCondition = false
            }.launchIn(viewModelScope)
        }
    }
}