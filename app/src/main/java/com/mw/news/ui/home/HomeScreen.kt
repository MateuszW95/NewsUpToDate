package com.mw.news.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.mw.news.base.State
import com.mw.news.models.articles.Article

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    ArticleList(viewModel)
}


@Composable
fun ArticleList(viewModel: HomeViewModel) {
    val viewState by remember(viewModel) { viewModel.topHeadlinesFlow }.collectAsState()
    val data = (viewState as? State.Success)?.data?.articles ?: emptyList()
    LazyColumn {
        items(
            items = data,
            itemContent = {
                ArticleListItem(article = it)
            })
    }
}

@Composable
fun ArticleListItem(article: Article) {
    Row {
        Column {
            Text(text = "${article.title}")
        }
    }
}