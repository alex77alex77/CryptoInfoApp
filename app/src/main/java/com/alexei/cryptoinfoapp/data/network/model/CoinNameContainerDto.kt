package com.alexei.cryptoinfoapp.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNameContainerDto (
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: CoinNameDto? = null
)
