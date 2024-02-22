package kz.avtobys.driverboard.auth.data.network.interceptor

import android.provider.Telephony
import com.google.gson.Gson
import kz.avtobys.common.utils.constants.ApiConstant
import kz.avtobys.common.utils.constants.ApiConstant.URLS_OF_UNNECESSARY_BEARER_TOKEN_ENDPOINTS
import kz.avtobys.driverboard.BuildConfig
import kz.avtobys.driverboard.auth.data.model.AuthRefreshTokenApiModel
import kz.avtobys.driverboard.auth.data.model.RefreshTokenApiModel
import kz.avtobys.driverboard.auth.data.network.SecurityDataSource
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import java.net.HttpURLConnection


class OAuthTokenInterceptor(
    private val securityDataSource: SecurityDataSource,
    private val gson: Gson,
): Interceptor {

    @Synchronized
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val apiRequest = chain.request()

        val apiRequestBuilder = apiRequest.newBuilder()

        // Добавляем токен в запрос
        if (!isUnnecessaryUrl(apiRequest)) {
            apiRequestBuilder.addOAuthHeader("${Telephony.Carriers.BEARER} ${securityDataSource.getAccessToken()}")
        }
        val response = chain.proceed(apiRequestBuilder.build())

        when {
            /**
             * При ответе если получаем 401 ошибку
             */
            !isUnnecessaryUrl(apiRequest) && response.code == HttpURLConnection.HTTP_UNAUTHORIZED -> {
                val refreshToken = securityDataSource.getRefreshToken().orEmpty()

                /**
                 * Если рефрешь токен пустой перекидываем на страницу ввода пароля и логина
                 */
                if (refreshToken.isEmpty()) {
                    clearAuthorizedUserData()
                    return response
                }

                /**
                 * Формируем модель для refresh token
                 */
                val refreshTokenBody = RefreshTokenApiModel(
                    refreshToken = refreshToken,
                    grantType = ApiConstant.GRANT_TYPE_REFRESH_TOKEN,
                )

                /**
                 * Отправляем запрос на refresh token
                 */
                val body = gson.toJson(refreshTokenBody).toString().toRequestBody()
                val refreshTokenRequest = apiRequest.newBuilder()
                    .post(body)
                    .url(BuildConfig.OAUTH_API_BASE_URL)
                    .addOAuthHeader(BuildConfig.CLIENT_SECRET)
                    .build()

                response.close()
                val refreshResponse = chain.proceed(refreshTokenRequest)

                /**
                 * При успешном запросе записываем данные в prefs
                 * в противном слуае чистим данные и отправляем на экран логина
                 */
                if (refreshResponse.isSuccessful) {
                    val refreshedToken = gson.fromJson(
                        refreshResponse.body?.string(),
                        AuthRefreshTokenApiModel::class.java,
                    )
                    securityDataSource.setAccessToken(refreshedToken.accessToken)
                    securityDataSource.setRefreshToken(refreshedToken.refreshToken)
                    securityDataSource.setTokenType(refreshedToken.tokenType)
                    val accessToken = refreshedToken.accessToken
                    val newCall =
                        apiRequest.newBuilder()
                            .addOAuthHeader("${Telephony.Carriers.BEARER} $accessToken").build()
                    refreshResponse.close()
                    return chain.proceed(newCall)
                } else if (refreshResponse.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    clearAuthorizedUserData()

                    return refreshResponse
                }

                return response.newBuilder().code(refreshResponse.code).build()
            }
        }

        return response
    }

    /**
     * Собераем url из сегментов для того чтобы сразнить есть ли собранный url
     * в списке исключений которому не требуеться токен
     * @param apiRequest
     */
    private fun isUnnecessaryUrl(apiRequest: Request): Boolean {
        var isUnnecessaryUrl = false
        URLS_OF_UNNECESSARY_BEARER_TOKEN_ENDPOINTS.forEach {
            if (apiRequest.url.toString().contains(it)) {
                isUnnecessaryUrl = true
            }
        }
        return isUnnecessaryUrl
    }

    /**
     * Происходит очистка всех данных о пользователе
     */
    private fun clearAuthorizedUserData() {
        securityDataSource.clearAuthorizedUserData()
    }

    /**
     * Добавление токена в хэдер запроса
     */
    private fun Request.Builder.addOAuthHeader(token: String?): Request.Builder {
        if ((token?.length ?: -1) > 7) {
            return header(ApiConstant.AUTHORIZATION, token.orEmpty())
        }
        return this
    }
}
