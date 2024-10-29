package com.languages.websocketcurrency.net

import com.languages.websocketcurrency.net.response.AuthResponse
import com.languages.websocketcurrency.net.response.InstrumentResponse
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface Api {

    @GET("/api/instruments/v1/instruments?provider=oanda&kind=forex")
    suspend fun getInstruments(
        @Header("Authorization") key: String,
    ): InstrumentResponse

    @FormUrlEncoded
    @POST("/identity/realms/{realm}/protocol/openid-connect/token")
    suspend fun userAuth(
        @Path("realm") realm: String,
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("username") username: String,
        @Field("password") password: String,
       // @Body body: AuthBody
    ): AuthResponse
}