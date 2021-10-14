package com.mw.news.network

import com.mw.news.global.Shared
import com.mw.news.models.articles.ArticlesResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IArticleApiService {

    //get all recent news in the pl
    @GET("top-headlines?country=pl")
    suspend fun getRecentNews(
        @Query("apiKey") apiKey: String = Shared.articleApiKey,
        @Query("page") page: Int?,
        @Query("pageSize") pageSize: Int
    ): ApiResponse<ArticlesResponse>


    //get all news when searching
    @GET("everything")
    suspend fun searchForNews(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String = Shared.articleApiKey
    ): ApiResponse<ArticlesResponse>
}