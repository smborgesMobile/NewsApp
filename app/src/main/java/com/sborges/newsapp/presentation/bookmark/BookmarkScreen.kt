package com.sborges.newsapp.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.sborges.newsapp.R
import com.sborges.newsapp.presentation.Dimens.MediumPaddingOne
import com.sborges.newsapp.presentation.common.ArticlesList
import com.sborges.newsapp.presentation.navGraph.Route
import com.sborges.newsapp.ui.theme.NewsAppTheme

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    navigate: (String) -> Unit,
    state: BookmarkState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                top = MediumPaddingOne,
                start = MediumPaddingOne,
                end = MediumPaddingOne
            )
    ) {
        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title)
        )

        ArticlesList(articles = state.articles) {
            navigate(Route.DetailsScreen.router)
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun BookmarkScreenPreview() {
    NewsAppTheme {
        BookmarkScreen(state = BookmarkState(listOf()), navigate = {

        })
    }
}