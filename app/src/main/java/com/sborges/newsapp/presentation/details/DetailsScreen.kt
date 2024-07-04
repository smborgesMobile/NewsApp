package com.sborges.newsapp.presentation.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sborges.newsapp.R
import com.sborges.newsapp.domain.model.Article
import com.sborges.newsapp.domain.model.Source
import com.sborges.newsapp.presentation.Dimens.ArticleImageHeight
import com.sborges.newsapp.presentation.Dimens.MediumPaddingOne
import com.sborges.newsapp.presentation.details.components.DetailsTopBar
import com.sborges.newsapp.ui.theme.NewsAppTheme

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    putExtra(Intent.EXTRA_TEXT, article.url)
                    type = "text/plain"
                }
                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                }
            },
            onBookmarkClick = { event(DetailsEvent.UpsertDeleteArticle(article)) },
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPaddingOne,
                end = MediumPaddingOne,
                top = MediumPaddingOne
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context)
                        .data(article.urlToImage)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium), contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(MediumPaddingOne))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(id = R.color.text_title)
                )

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.displayMedium,
                    color = colorResource(id = R.color.text_title)
                )

                Text(
                    text = article.description,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun DetailsScreenPreview() {
    NewsAppTheme {
        DetailsScreen(article = Article(
            author = "Author",
            content = "Content",
            description = "Description",
            publishedAt = "10 hours",
            source = Source("", "ReadWrite"),
            title = "VanEck Set to Launch Spot Bitcoin ETF on Australia’s ASX\"",
            url = "https://readwrite.com/bitcoin-trades-above-supports-bank-collapses-are-a-good-omen/",
            urlToImage = "https://readwrite.com/wp-content/uploads/2024/06/fc23b9c7-9438-4ba4-a436-fabd4fed874e.webp"
        ), event = {}) {

        }
    }
}