package com.mw.news.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mw.news.base.State
import com.mw.news.models.articles.ArticlesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val getTopHeadlinesUseCase: GetTopHeadlinesUseCase) :
    ViewModel() {

    val pagingParam = GetTopHeadlinesUseCase.Params(page = 1, pageSize = 40)

    val topHeadlinesFlow: StateFlow<State<ArticlesResponse>> = flow {
        emit(State.Loading)
        emit(getTopHeadlinesUseCase.run(pagingParam))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), State.Loading)
}