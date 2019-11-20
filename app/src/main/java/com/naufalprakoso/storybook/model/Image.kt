package com.naufalprakoso.storybook.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    val image: String = ""
): Parcelable