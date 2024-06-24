package com.sborges.newsapp.presentation.navGraph

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.sborges.newsapp.presentation.onboarding.OnboardingViewModel
import com.sborges.newsapp.presentation.onboarding.screens.OnboardingScreen

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()

    /**
     * Defines which is my nav host an the first router I need to use.
     */
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.router,
            startDestination = Route.Onboarding.router
        ) {
            composable(
                route = Route.Onboarding.router
            ) {
                val viewModel: OnboardingViewModel = hiltViewModel()
                OnboardingScreen(event = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.router,
            startDestination = Route.NewsNavigationScreen.router
        ) {
            composable(
                route = Route.NewsNavigationScreen.router
            ) {
                Column {
                    Text(text = "Home Will be Here")
                    Text(text = "Home Will be here")
                    Text(text = "Home Will be here")
                    Text(text = "Home Will be here")
                    Text(text = "Home Will be here")
                    Text(text = "Home Will be here")
                }
            }
        }
    }
}