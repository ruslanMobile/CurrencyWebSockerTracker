package com.languages.websocketcurrency.data.repository

import android.util.Log
import com.languages.websocketcurrency.domain.repository.GetInstrumentsDataRepository
import com.languages.websocketcurrency.net.Api
import com.languages.websocketcurrency.net.NetworkRequestHandler
import com.languages.websocketcurrency.net.ResultWrapper
import com.languages.websocketcurrency.net.response.InstrumentResponseModel
import com.languages.websocketcurrency.utils.PreferenceDataStoreManager
import com.languages.websocketcurrency.utils.PreferenceDataStoreManager.Keys.AUTH_TOKEN
import javax.inject.Inject

class GetInstrumentsDataRepositoryImpl @Inject constructor(
    private val api: Api,
    private val preferenceDataStoreManager: PreferenceDataStoreManager
) : GetInstrumentsDataRepository {


    override suspend fun getInstrumentsList(): List<InstrumentResponseModel>? {
        val res = NetworkRequestHandler.doRequest(request = {
            api.getInstruments(
                "Bearer ${preferenceDataStoreManager.readString(AUTH_TOKEN)}"
            )
        })

        if (res is ResultWrapper.Success) {
            return res.data.data
        }
        return null
    }
}