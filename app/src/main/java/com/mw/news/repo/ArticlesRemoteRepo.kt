package com.mw.news.repo

import com.mw.news.network.dto.articles.ArticlesResponseDto
import com.skydoves.sandwich.ApiResponse

interface ArticlesRemoteRepo {

    suspend fun getTopHeadlines(page: Int, pageSize: Int): ApiResponse<ArticlesResponseDto>
}