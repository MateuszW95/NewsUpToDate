package com.mw.news.repo

import com.mw.news.models.articles.ArticlesResponse
import com.skydoves.sandwich.ApiResponse

interface ArticlesRemoteRepo {

    suspend fun getTopHeadlines(page: Int, pageSize: Int): ApiResponse<ArticlesResponse>
}