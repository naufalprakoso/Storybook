package com.naufalprakoso.storybook.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Frame(
    val image: String = "",
    val datetime: String = "",
    val title: String = "",
    val id: String = "",
    val type: String = ""
) : Parcelable