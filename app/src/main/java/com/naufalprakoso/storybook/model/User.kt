package com.naufalprakoso.storybook.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val username: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = ""
) : Parcelable