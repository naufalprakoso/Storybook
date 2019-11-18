package com.naufalprakoso.storybook.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Story(
    val id: String
) : Parcelable