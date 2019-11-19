package com.naufalprakoso.storybook.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comment(
    val comment: String = "",
    val userId: String = "",
    val datetime: String = ""
) : Parcelable