package com.sborges.newsapp.presentation.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sborges.newsapp.presentation.Dimens.extraSmallTwo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerList(
    modifier: Modifier = Modifier,
    bannerList: List<String>
) {
    val pagerState = rememberPagerState(pageCount = {
        bannerList.size
    })

    Column(modifier = Modifier) {
        HorizontalPager(
            state = pagerState,
            modifier = modifier,
            contentPadding = PaddingValues(
                start = if (pagerState.currentPage == 0) 16.dp else 32.dp, // Reduced start margin for the first card
                end = 32.dp
            )
        ) { page ->
            BannerCard(imageUrl = bannerList[page])
        }

        Spacer(modifier = Modifier.height(extraSmallTwo))

        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color.DarkGray else
                    Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun BannerListPreview() {
    BannerList(
        modifier = Modifier,
        bannerList = listOf("a", "b", "c")
    )
}