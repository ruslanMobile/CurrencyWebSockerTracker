package com.languages.websocketcurrency.net.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
   @SerializedName("access_token") val accessToken: String
)