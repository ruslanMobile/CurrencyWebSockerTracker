package com.languages.websocketcurrency.domain.repository

import com.languages.websocketcurrency.net.ResultWrapper
import com.languages.websocketcurrency.net.response.AuthResponse

interface AuthRepository {

    suspend fun userAuthorization(): ResultWrapper<AuthResponse>

}