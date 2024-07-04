package com.sborges.newsapp.presentation.newsNavigator.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sborges.newsapp.R
import com.sborges.newsapp.presentation.Dimens.extraSmallTwo
import com.sborges.newsapp.presentation.Dimens.smallIconSize
import com.sborges.newsapp.ui.theme.NewsAppTheme

@Composable
fun NewsBottomNavigation(
    modifier: Modifier = Modifier,
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit
) {

    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(smallIconSize)
                        )
                        Spacer(modifier = Modifier.height(extraSmallTwo))
                        Text(
                            text = item.text,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = colorResource(id = R.color.body),
                    unselectedIconColor = colorResource(id = R.color.body),
                )
            )

        }
    }
}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)

@Composable
@Preview(showBackground = true)
private fun BottomNavigationPreview() {
    NewsAppTheme {
        NewsBottomNavigation(
            items = listOf(
                BottomNavigationItem(
                    icon = R.drawable.ic_home,
                    text = "Home"
                ),
                BottomNavigationItem(
                    icon = R.drawable.ic_search,
                    text = "Serch"
                ),
                BottomNavigationItem(
                    icon = R.drawable.ic_bookmark,
                    text = "Bookmark"
                )
            ),
            selected = 0,
            onItemClick = {}
        )
    }
}