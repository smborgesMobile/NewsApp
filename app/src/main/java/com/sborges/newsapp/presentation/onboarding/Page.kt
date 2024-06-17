package com.sborges.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.sborges.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        "Discover New Features",
        "Effortlessly manage your tasks and stay on top of your schedule with our intuitive tools.",
        R.drawable.onboarding1
    ),
    Page(
        "Stay Organized",
        "Effortlessly manage your tasks and stay on top of your schedule with our intuitive tools.",
        R.drawable.onboarding2
    ),
    Page(
        "Connect with Community",
        "Effortlessly manage your tasks and stay on top of your schedule with our intuitive tools.",
        R.drawable.onboarding3
    )
)