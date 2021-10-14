package com.mw.news.models.articles


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Serializable
@Parcelize
data class ArticlesResponse(
    @SerialName("articles")
    val articles: List<Article>? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("totalResults")
    val totalResults: Int? = null
) : Parcelable