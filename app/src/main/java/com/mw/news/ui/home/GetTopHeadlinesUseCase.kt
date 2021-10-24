package com.mw.news.ui.home

import com.mw.news.base.BaseUseCaseWithParams
import com.mw.news.base.State
import com.mw.news.network.dto.articles.ArticlesResponseDto
import com.mw.news.repo.ArticlesRemoteRepo
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(val repo: ArticlesRemoteRepo) :
    BaseUseCaseWithParams<GetTopHeadlinesUseCase.Params, State<ArticlesResponseDto>> {

    override suspend fun run(params: Params): State<ArticlesResponseDto> {
        return repo.getTopHeadlines(page = params.page, pageSize = params.pageSize)
            .let { response ->
                when (response) {
                    is ApiResponse.Success -> State.Success(response.data)
                    is ApiResponse.Failure.Error -> State.Error(Exception(response.errorBody?.toString()))
                    is ApiResponse.Failure.Exception -> State.Error(response.exception)
                }
            }
    }

    data class Params(
        var page: Int,
        val pageSize: Int
    )
}