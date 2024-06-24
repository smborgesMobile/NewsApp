package com.sborges.newsapp.presentation.onboarding.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sborges.newsapp.presentation.Dimens.MediumPaddingTwo
import com.sborges.newsapp.presentation.Dimens.PageIndicatorWidth
import com.sborges.newsapp.presentation.common.NewsButton
import com.sborges.newsapp.presentation.common.NewsTextButton
import com.sborges.newsapp.presentation.common.PagerIndicator
import com.sborges.newsapp.presentation.onboarding.OnboardEvent
import com.sborges.newsapp.presentation.onboarding.components.OnboardingPage
import com.sborges.newsapp.presentation.onboarding.pages
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    event: (OnboardEvent) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        val pageState = rememberPagerState(initialPage = 0) {
            pages.size
        }

        val buttonState = remember {
            derivedStateOf {
                when (pageState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(state = pageState) { index ->
            OnboardingPage(page = pages[index])
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPaddingTwo)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PagerIndicator(
                modifier = Modifier.width(PageIndicatorWidth),
                pagesSize = pages.size,
                selectedPage = pageState.currentPage
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                val scope = rememberCoroutineScope()
                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(text = buttonState.value[0]) {
                        scope.launch {
                            pageState.animateScrollToPage(page = pageState.currentPage - 1)
                        }
                    }
                }

                NewsButton(text = buttonState.value[1]) {
                    scope.launch {
                        if (pageState.currentPage == 2) {
                            event(OnboardEvent.SaveAppEntry)
                        } else {
                            pageState.animateScrollToPage(page = pageState.currentPage + 1)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    MaterialTheme {
        OnboardingScreen {}
    }
}