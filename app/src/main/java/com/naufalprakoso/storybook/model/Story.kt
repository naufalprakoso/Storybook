package com.naufalprakoso.storybook.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Story(
    var id: String = "",
    val datetime: String = "",
    val featuredImage: String = "",
    val likes: Int = 0,
    val title: String = "",
    val userId: String = "",
    val username: String = "",
    val comments: List<Comment> = listOf(),
    val images: List<Image> = listOf()
) : Parcelable