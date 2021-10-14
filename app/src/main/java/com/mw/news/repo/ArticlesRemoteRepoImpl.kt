package com.mw.news.repo

import com.mw.news.models.articles.ArticlesResponse
import com.mw.news.network.IArticleApiService
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class ArticlesRemoteRepoImpl @Inject constructor(val service: IArticleApiService) :
    ArticlesRemoteRepo {

    override suspend fun getTopHeadlines(page: Int, pageSize: Int): ApiResponse<ArticlesResponse> =
        service.getRecentNews(page = page, pageSize = pageSize)
}