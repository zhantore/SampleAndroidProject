package kz.avtobys.driverboard.auth.data.network

import kz.avtobys.driverboard.auth.data.model.AuthTokenResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthDataSource {

    @FormUrlEncoded
    @POST("/oauth/token")
    suspend fun getAccessToken(
        @Header("Authorization") basicToken: String,
        @Field("grant_type") grantType: String,
        @Field("username") userName: String,
        @Field("password") password: String,
    ): Response<AuthTokenResponse>

    @FormUrlEncoded
    @POST("/oauth/token")
    suspend fun refreshToken(
        @Header("Authorization") authorization: String,
        @Field("lang") lang: String,
        @Field("grant_type") grantType: String,
        @Field("username") userName: String,
        @Field("password") password: String
    ): Response<AuthTokenResponse>
}