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

/**
 * Defines the navigation graph for the News section of the application.
 * This includes the initial route and the composable screen associated with it.
 *
 * <p>This navigation graph is nested within the main navigation graph and handles
 * the routes specific to the News section. The start destination for this
 * navigation graph is the `Route.NewsNavigationScreen.router`.
 *
 * <p>When the `Route.NewsNavigation.router` is navigated to, it will start at the
 * `Route.NewsNavigationScreen.router`, displaying a simple screen with a column
 * of text elements.
 *
 * <pre>{@code
 * navigation(
 *     route = Route.NewsNavigation.router,
 *     startDestination = Route.NewsNavigationScreen.router
 * ) {
 *     composable(
 *         route = Route.NewsNavigationScreen.router
 *     ) {
 *         Column {
 *             Text(text = "Home Will be Here")
 *             Text(text = "Home Will be here")
 *             Text(text = "Home Will be here")
 *             Text(text = "Home Will be here")
 *             Text(text = "Home Will be here")
 *             Text(text = "Home Will be here")
 *         }
 *     }
 * }
 * }</pre>
 *
 * <p>The navigation structure allows for modularity and organization within the
 * application, making it easier to manage different sections independently.
 *
 * <p>Key components:
 * <ul>
 *     <li><b>navigation:</b> Defines a nested navigation graph with a specific route and start destination.</li>
 *     <li><b>composable:</b> Defines a composable screen associated with a specific route.</li>
 *     <li><b>Column:</b> A layout composable that arranges its children in a vertical sequence.</li>
 *     <li><b>Text:</b> A composable that displays text on the screen.</li>
 * </ul>
 *
 * @see androidx.navigation.NavHost
 * @see androidx.navigation.NavController
 * @see androidx.compose.runtime.Composable
 */

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()

    /**
     * Defines which is my nav host an the first router I need to use.
     */
    NavHost(navController = navController, startDestination = startDestination) {

        /**
         * Defines the graphs inside this NavHot.
         *
         * For example, here, we have graph from OnboardingScreen to AppStartNavigation
         *
         * The first parameter is the route name and the second is the start destination.
         */
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