package com.languages.websocketcurrency.domain.repository

import com.languages.websocketcurrency.net.response.InstrumentResponseModel

interface GetInstrumentsDataRepository {

    suspend fun getInstrumentsList(): List<InstrumentResponseModel>?
}