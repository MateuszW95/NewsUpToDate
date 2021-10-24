package com.mw.news.network.dto.articles


import android.os.Parcelable
import com.mw.news.extensions.displayDateWith
import com.mw.news.extensions.toLocalDate
import com.mw.news.global.Shared
import com.mw.news.models.ArticleModel
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class ArticleDto(
    @SerialName("author")
    val author: String? = null,
    @SerialName("content")
    val content: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("publishedAt")
    val publishedAt: String? = null,
    @SerialName("source")
    val source: SourceDto? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("urlToImage")
    val urlToImage: String? = null
) : Parcelable {

    fun toArticleModel(): ArticleModel =
        ArticleModel(
            title = title,
            dateString = publishedAt?.toLocalDate(pattern = Shared.dateISOFormat)
                ?.displayDateWith(Shared.dateNewsFormat) ?: "",
            author = author,
            image = urlToImage
        )

}