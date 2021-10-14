package com.mw.news.models.articles


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Source(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null
) : Parcelable