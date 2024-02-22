package kz.avtobys.driverboard.auth.data.model

import com.google.gson.annotations.SerializedName

data class AuthRequestApiModel(
    @SerializedName("grant_type") val grantType: String,
    @SerializedName("username") val userName: String,
    @SerializedName("password") val password: String,
)