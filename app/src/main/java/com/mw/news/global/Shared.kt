package com.mw.news.global

import com.mw.news.BuildConfig

object Shared {

    const val articleApiKey = BuildConfig.API_KEY
    const val articlesApiBaseUrl="https://newsapi.org/v2/"

    const val dateISOFormat="yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val dateNewsFormat="dd MMMM yyyy"
}