package com.makeup.makeupapptest.home.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ProductColor(
    @SerializedName("colour_name")
    val colourName: String?,
    @SerializedName("hex_value")
    val hexValue: String?
) : Parcelable