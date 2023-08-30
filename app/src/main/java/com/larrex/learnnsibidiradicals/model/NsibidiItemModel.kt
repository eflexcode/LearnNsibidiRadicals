package com.larrex.learnnsibidiradicals.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NsibidiItemModel(
    val imageId: Int, val word: String
    ): Parcelable
