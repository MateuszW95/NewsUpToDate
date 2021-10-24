package com.mw.news.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mw.news.base.State
import com.mw.news.models.ArticleModel
import com.mw.news.widgets.ProgressView

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    val progressState by remember(viewModel) { viewModel.viewStateFlow }.collectAsState()
    LaunchedEffect(Unit) { //is executed only on the first recomposition because the key parameter is constant (Unit)
        viewModel.getInitialData()
    }
    ProgressView(showProgress = progressState is State.Loading, modifier = Modifier.fillMaxSize()) {
        ArticleList(viewModel)
    }
}


@Composable
fun ArticleList(viewModel: HomeViewModel) {
    val data by remember(viewModel) { viewModel.articlesFlow }.collectAsState()
    LazyColumn {
        items(
            items = data,
            itemContent = {
                ArticleListItem(article = it)
            })
    }
}

@Composable
fun ArticleListItem(article: ArticleModel) {
    Row {
        Column {
            Text(text = "${article.title}")
        }
    }
}