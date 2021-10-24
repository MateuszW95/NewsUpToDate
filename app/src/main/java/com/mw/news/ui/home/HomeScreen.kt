package com.mw.news.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
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
    val listState = rememberLazyListState()

    if(listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index==data.lastIndex){
        viewModel.loadMoreArticles()
    }

    LazyColumn(state = listState) {
        items(
            items = data,
            itemContent = {
                ArticleListItem(article = it)
            })

    }
}

@Preview
@Composable
fun ArticleListItem(
    article: ArticleModel = ArticleModel(
        title = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
        dateString = "2020-10-11",
        author = "Test Author",
        image = ""
    )
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 5.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.78f),
                painter = rememberImagePainter(
                    data = article.image,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .padding(top = 3.dp),
                text = "${article.dateString}",
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .padding(bottom = 3.dp),
                text = "${article.title}",
                fontSize = 16.sp,
                fontStyle = FontStyle.Normal,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}