package kz.avtobys.driverboard.auth.data.model

import com.google.gson.annotations.SerializedName

data class RefreshTokenRequest(
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("grant_type") val grantType: String,
)
