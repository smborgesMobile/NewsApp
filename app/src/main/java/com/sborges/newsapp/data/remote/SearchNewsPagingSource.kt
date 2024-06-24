package com.sborges.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sborges.newsapp.domain.model.Article

class SearchNewsPagingSource(
    private val api: NewsApi,
    private val searchQuery: String,
    private val sources: String
) : PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val apiResponse = api.searchNews(
                sources = sources,
                query = searchQuery,
                page = page
            )

            totalNewsCount += apiResponse.articles.size

            val articles = apiResponse.articles.distinctBy { it.title }

            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == apiResponse.totalResults) {
                    null
                } else {
                    page + 1
                },
                prevKey = null
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            LoadResult.Error(throwable = ex)
        }
    }
}