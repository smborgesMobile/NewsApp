package com.sborges.newsapp.presentation.navGraph

sealed class Route(val router: String) {
    data object Onboarding : Route("onboardingScreen")
    data object HomeScreen : Route("homeScreen")
    data object SearchScreen : Route("searchScreen")
    data object BookmarkScreen : Route("bookMarkScreen")
    data object DetailsScreen : Route("detailsScreen")


    data object AppStartNavigation : Route("appStartNavigation")
    data object NewsNavigation : Route("newsNavigation")
    data object NewsNavigationScreen : Route("newsNavigationScreen")
    data object SearchNavigation : Route("searchNavigationScreen")
    data object DetailNavigation : Route("detailNavigation")
}