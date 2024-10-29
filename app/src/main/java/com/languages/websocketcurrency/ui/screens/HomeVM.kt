package com.languages.websocketcurrency.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.languages.websocketcurrency.domain.repository.AuthRepository
import com.languages.websocketcurrency.domain.repository.GetInstrumentsDataRepository
import com.languages.websocketcurrency.net.ResultWrapper
import com.languages.websocketcurrency.net.response.InstrumentResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val getInstrumentsRepository: GetInstrumentsDataRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    val authState = MutableStateFlow<AuthState>(AuthState.Unauthorized)
    val instrumentsState = MutableStateFlow<List<InstrumentResponseModel>?>(listOf())

    init {
        userAuth()
    }

    private fun userAuth() = viewModelScope.launch {
        authState.emit(AuthState.Authorization)
        delay(2000)
        val res = authRepository.userAuthorization()
        if (res is ResultWrapper.Success) {
            authState.emit(AuthState.Authorized)
            getInstrumentsList()
        } else {
            authState.emit(AuthState.AuthorizationError)
        }
    }

    private fun getInstrumentsList() = viewModelScope.launch {
        val res = getInstrumentsRepository.getInstrumentsList()
        instrumentsState.emit(res)
        Log.e("MyLog", "getInstrumentsList: $res")
    }
}

sealed class AuthState(val message: String) {
    data object Unauthorized : AuthState("Unauthorized")
    data object Authorization : AuthState("Authorization")
    data object Authorized : AuthState("Authorized")
    data object AuthorizationError : AuthState("AuthorizationError")
}