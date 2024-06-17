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
        "Explore the latest updates and features designed to enhance your experience.",
        R.drawable.onboarding1
    ),
    Page(
        "Stay Organized",
        "Effortlessly manage your tasks and stay on top of your schedule with our intuitive tools.",
        R.drawable.onboarding2
    ),
    Page(
        "Connect with Community",
        "Join a vibrant community of users and share your experiences and insights.",
        R.drawable.onboarding3
    )
)