package kz.avtobys.driverboard.auth.data.model

import com.google.gson.annotations.SerializedName

data class AuthTokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName ("expires_in") val expiresIn: Int,
    @SerializedName ("scope") val scope: String,
    @SerializedName("jti") val jti: String,
)
