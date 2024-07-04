package com.sborges.newsapp.presentation.newsNavigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.sborges.newsapp.R
import com.sborges.newsapp.domain.model.Article
import com.sborges.newsapp.presentation.bookmark.BookmarkScreen
import com.sborges.newsapp.presentation.bookmark.BookmarkViewModel
import com.sborges.newsapp.presentation.details.DetailsEvent
import com.sborges.newsapp.presentation.details.DetailsScreen
import com.sborges.newsapp.presentation.details.DetailsViewModel
import com.sborges.newsapp.presentation.home.HomeScreen
import com.sborges.newsapp.presentation.home.HomeViewModel
import com.sborges.newsapp.presentation.navGraph.Route
import com.sborges.newsapp.presentation.newsNavigator.components.BottomNavigationItem
import com.sborges.newsapp.presentation.newsNavigator.components.NewsBottomNavigation
import com.sborges.newsapp.presentation.search.SearchScreen
import com.sborges.newsapp.presentation.search.SearchViewModel

@Composable
fun NewsNavigator() {

    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(
                icon = R.drawable.ic_home,
                text = "Home"
            ),
            BottomNavigationItem(
                icon = R.drawable.ic_search,
                text = "Serch"
            ),
            BottomNavigationItem(
                icon = R.drawable.ic_bookmark,
                text = "Bookmark"
            )
        )
    }


    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.HomeScreen.router -> 0
            Route.SearchScreen.router -> 1
            Route.BookmarkScreen.router -> 2
            else -> 0
        }
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        val backStackRoute = backStackState?.destination?.route
        backStackRoute == Route.SearchScreen.router ||
            backStackRoute == Route.BookmarkScreen.router ||
            backStackRoute == Route.HomeScreen.router
    }

    Scaffold(
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItem,
                    selected = selectedItem
                ) { index ->
                    when (index) {
                        0 -> navigateToTap(navController, Route.HomeScreen.router)
                        1 -> navigateToTap(navController, Route.SearchScreen.router)
                        2 -> navigateToTap(navController, Route.BookmarkScreen.router)
                    }
                }
            }
        }
    ) { padding ->

        val bottomPadding = padding.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.router,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(Route.HomeScreen.router) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToDetail = { navigateToDetail(navController, it) },
                    navigateToSearch = {
                        navigateToTap(navController, Route.SearchScreen.router)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(
                route = Route.SearchScreen.router
            ) {
                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(state = viewModel.state.value, event = viewModel::onEvent) {
                    navigateToDetail(navController, it)
                }
            }

            composable(route = Route.DetailsScreen.router) {
                val viewModel: DetailsViewModel = hiltViewModel()
                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT)
                        .show()
                    viewModel.onEvent(DetailsEvent.RemovingSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
                    ?.let { article ->
                        DetailsScreen(article = article, event = viewModel::onEvent) {
                            navController.navigateUp()
                        }
                    }
            }

            composable(route = Route.BookmarkScreen.router) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(navigateToDetails = {
                    navigateToDetail(
                        navController = navController,
                        article = it
                    )
                }, state = state)
            }
        }
    }
}

private fun navigateToTap(
    navController: NavController,
    route: String
) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

private fun navigateToDetail(
    navController: NavController,
    article: Article
) {
    navController.currentBackStackEntry?.savedStateHandle?.set(
        "article",
        article
    )

    navController.navigate(
        route = Route.DetailsScreen.router
    )
}