package com.mw.news.network.dto.articles


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.mw.news.models.ArticleModel

@Serializable
@Parcelize
data class ArticlesResponseDto(
    @SerialName("articles")
    val articles: List<ArticleDto>? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("totalResults")
    val totalResults: Int? = null
) : Parcelable