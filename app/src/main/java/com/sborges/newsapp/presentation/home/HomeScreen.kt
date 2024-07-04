package com.sborges.newsapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.sborges.newsapp.R
import com.sborges.newsapp.domain.model.Article
import com.sborges.newsapp.presentation.Dimens.MediumPaddingOne
import com.sborges.newsapp.presentation.common.ArticlesList
import com.sborges.newsapp.presentation.common.SearchBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    navigateToDetail: (Article) -> Unit,
    navigateToSearch: () -> Unit
) {

    val title by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " - ") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = MediumPaddingOne)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "",
            modifier = Modifier
                .width(140.dp)
                .height(40.dp)
                .padding(horizontal = MediumPaddingOne),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(MediumPaddingOne))

        SearchBar(
            modifier = Modifier.padding(horizontal = MediumPaddingOne),
            text = "",
            readOnly = true,
            onClick = { navigateToSearch() },
            onValueChange = {}
        ) {

        }

        Spacer(modifier = Modifier.height(MediumPaddingOne))

        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPaddingOne)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(MediumPaddingOne))

        ArticlesList(
            modifier = Modifier.padding(horizontal = MediumPaddingOne),
            articles = articles
        ) {
            navigateToDetail(it)
        }
    }

}