package com.makeup.makeupapptest.home.models

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class SortData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("product_type")
    val productType: String
) : Parcelable
