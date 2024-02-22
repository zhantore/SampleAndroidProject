package kz.avtobys.common.utils.constants

object ApiConstant {

    const val GRANT_TYPE_PASSWORD = "password"
    const val GRANT_TYPE_REFRESH_TOKEN = "refresh_token"
    const val AUTHORIZATION = "Authorization"
    private const val AUTH_URL = "/oauth/token"

    val URLS_OF_UNNECESSARY_BEARER_TOKEN_ENDPOINTS = listOf(
        AUTH_URL,
    )
}