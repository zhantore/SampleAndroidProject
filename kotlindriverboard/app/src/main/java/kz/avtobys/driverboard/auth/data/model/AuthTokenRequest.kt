package kz.avtobys.driverboard.auth.data.model

import com.google.gson.annotations.SerializedName

data class AuthTokenRequest(
    @SerializedName("grant_type") val grantType: String,
    @SerializedName("username") val userName: String,
    @SerializedName("password") val password: String,
)