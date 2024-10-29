package com.languages.websocketcurrency.data.repository

import android.util.Log
import com.languages.websocketcurrency.domain.repository.AuthRepository
import com.languages.websocketcurrency.net.Api
import com.languages.websocketcurrency.net.NetService
import com.languages.websocketcurrency.net.NetworkRequestHandler
import com.languages.websocketcurrency.net.ResultWrapper
import com.languages.websocketcurrency.net.response.AuthResponse
import com.languages.websocketcurrency.utils.AuthConst.FIELD_CLIENT_ID
import com.languages.websocketcurrency.utils.AuthConst.FIELD_GRAND_TYPE
import com.languages.websocketcurrency.utils.AuthConst.FIELD_PASSWORD
import com.languages.websocketcurrency.utils.AuthConst.FIELD_REALM
import com.languages.websocketcurrency.utils.AuthConst.FIELD_USER_NAME
import com.languages.websocketcurrency.utils.PreferenceDataStoreManager
import com.languages.websocketcurrency.utils.PreferenceDataStoreManager.Keys.AUTH_TOKEN
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: Api,
    private val preferenceDataStoreManager: PreferenceDataStoreManager
) : AuthRepository {

    override suspend fun userAuthorization(): ResultWrapper<AuthResponse> {
        val res = NetworkRequestHandler.doRequest(request = {
            api.userAuth(
                realm = FIELD_REALM,
                grantType = FIELD_GRAND_TYPE,
                clientId = FIELD_CLIENT_ID,
                username = FIELD_USER_NAME,
                password = FIELD_PASSWORD
            )
        })

        if (res is ResultWrapper.Success) {
            preferenceDataStoreManager.saveString(AUTH_TOKEN, res.data.accessToken)
        }
        return res
    }
}