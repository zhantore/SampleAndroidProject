package kz.avtobys.driverboard.auth.domain.model

data class AuthTokenResponseData(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String,
    val jti: String,
)
