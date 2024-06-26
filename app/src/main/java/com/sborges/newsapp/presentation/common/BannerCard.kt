package com.sborges.newsapp.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sborges.newsapp.presentation.Dimens.MediumRoundedCorner
import com.sborges.newsapp.presentation.Dimens.extraSmallTwo

@Composable
fun BannerCard(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(horizontal = extraSmallTwo)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier.clip(
                RoundedCornerShape(MediumRoundedCorner)
            ),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
@Preview(showBackground = true)
fun BannerPreview() {
    BannerCard(imageUrl = "https://sustentabilidadeagora.com.br/wp-content/uploads/2022/07/marcanatura-1024x576.jpg")
}
