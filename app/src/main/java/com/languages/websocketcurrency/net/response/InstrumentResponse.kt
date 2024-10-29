package com.languages.websocketcurrency.net.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InstrumentResponse(
    val data: List<InstrumentResponseModel>
): Parcelable

@Parcelize
data class InstrumentResponseModel(
    val id: String,
    val symbol: String
): Parcelable
