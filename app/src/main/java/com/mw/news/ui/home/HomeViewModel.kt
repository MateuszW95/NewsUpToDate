package com.mw.news.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mw.news.base.State
import com.mw.news.models.ArticleModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val getTopHeadlinesUseCase: GetTopHeadlinesUseCase) :
    ViewModel() {

    val pagingParam = GetTopHeadlinesUseCase.Params(page = 1, pageSize = 10)

    private var _canLoadMoreItems = false
    private val _articles = arrayListOf<ArticleModel>()

    private val _viewStateFlow: MutableStateFlow<State<Nothing?>> = MutableStateFlow(State.Loading)
    private val _articlesFlow = MutableStateFlow(emptyList<ArticleModel>())

    private val _pagingRequestActive= MutableStateFlow(true)


    val viewStateFlow: StateFlow<State<Nothing?>>
        get() = _viewStateFlow

    val articlesFlow: StateFlow<List<ArticleModel>>
        get() = _articlesFlow


    fun getInitialData() {
        viewModelScope.launch(Dispatchers.IO) {
            _pagingRequestActive.emit(true)
        }
    }
    init {
        viewModelScope.launch {
            _pagingRequestActive.collect {
                if(it){
                    getTopHeadLinesArticleList()
                }
            }
        }
    }

    fun loadMoreArticles() {
        Log.d("#Compose", "loadMore can? -> $_canLoadMoreItems")
        if (_canLoadMoreItems) {
            pagingParam.page += 1
            viewModelScope.launch {
                _pagingRequestActive.emit(true)
            }
        }
    }


    private suspend fun getTopHeadLinesArticleList() {
        _viewStateFlow.emit(State.Loading)
        when (val response = getTopHeadlinesUseCase.run(pagingParam)) {
            is State.Success -> {
                _articles.addAll(response.data.articles?.map { it.toArticleModel() } ?: emptyList())
                _articlesFlow.emit(ArrayList(_articles))
                _viewStateFlow.emit(State.Success(null))
                _canLoadMoreItems =
                    (response.data.totalResults ?: 0) > pagingParam.page * pagingParam.pageSize
            }
            is State.Error -> _viewStateFlow.emit(response)
        }
        _pagingRequestActive.emit(false)
    }

}