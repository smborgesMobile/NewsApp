package com.sborges.newsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.sborges.newsapp.domain.model.Article
import com.sborges.newsapp.presentation.Dimens.MediumPaddingOne
import com.sborges.newsapp.presentation.Dimens.SmallSpacingTop
import com.sborges.newsapp.presentation.common.ArticlesList
import com.sborges.newsapp.presentation.common.BannerList
import com.sborges.newsapp.presentation.common.SearchBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
        ) {
            BannerList(
                modifier = Modifier.padding(
                    top = SmallSpacingTop
                ),
                bannerList = listOf(
                    "https://mainstreetdesigns.com/wp-content/uploads/2023/09/MainStreet-Designs-Banners-Page-Custom-Image-1000x423.jpg",
                    "https://static.vecteezy.com/system/resources/previews/008/859/038/non_2x/promotion-banner-for-natural-beauty-products-skincare-mockup-with-tropic-leaves-advertising-template-scene-for-serum-in-a-brown-transparent-glass-bottle-with-pipette-essential-oil-3d-illustration-free-vector.jpg ",
                    "https://static.vecteezy.com/system/resources/previews/008/859/039/original/promotion-banner-for-natural-beauty-products-skincare-mockups-with-green-tropic-leaves-advertising-template-scene-for-serum-in-a-brown-glass-bottle-essential-oil-3d-illustration-free-vector.jpg",
                    "https://static.vecteezy.com/system/resources/previews/008/859/038/non_2x/promotion-banner-for-natural-beauty-products-skincare-mockup-with-tropic-leaves-advertising-template-scene-for-serum-in-a-brown-transparent-glass-bottle-with-pipette-essential-oil-3d-illustration-free-vector.jpg ",
                    "https://static.vecteezy.com/system/resources/previews/008/859/039/original/promotion-banner-for-natural-beauty-products-skincare-mockups-with-green-tropic-leaves-advertising-template-scene-for-serum-in-a-brown-glass-bottle-essential-oil-3d-illustration-free-vector.jpg"
                )
            )
        }
        Column(
            modifier = modifier
                .padding(
                    top = SmallSpacingTop,
                    start = MediumPaddingOne,
                    end = MediumPaddingOne
                )
                .statusBarsPadding()
        ) {
            SearchBar(
                text = state.searchQuery,
                readOnly = false,
                onClick = { },
                onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
                onSearch = { event(SearchEvent.SearchNews) }
            )
            Spacer(modifier = Modifier.height(MediumPaddingOne))
            state.articles?.let { article ->
                val articles = article.collectAsLazyPagingItems()
                ArticlesList(articles = articles) {
                    navigateToDetails(it)
                }
            }
            Spacer(modifier = Modifier.height(MediumPaddingOne))
        }
    }
}