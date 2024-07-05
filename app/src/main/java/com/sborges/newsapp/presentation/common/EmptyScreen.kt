package com.sborges.newsapp.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sborges.newsapp.R

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search_document),
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp),
                tint = colorResource(id = R.color.placeholder)
            )
            Spacer(modifier = modifier.height(40.dp))
            Text(
                text = "You don't have any saved article",
                style = MaterialTheme.typography.titleMedium,
                color = colorResource(id = R.color.placeholder)
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
private fun EmptyScreenPreview() {
    EmptyScreen()
}