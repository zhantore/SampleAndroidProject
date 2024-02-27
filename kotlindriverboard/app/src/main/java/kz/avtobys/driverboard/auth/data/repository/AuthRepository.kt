package kz.avtobys.driverboard.auth.data.repository

import kz.avtobys.core.data.constants.ApiConstant.GRANT_TYPE_PASSWORD
import kz.avtobys.driverboard.BuildConfig
import kz.avtobys.driverboard.auth.data.cache.SecurityLocalCache
import kz.avtobys.driverboard.auth.data.mapper.AuthRefreshTokenApiModelMapper
import kz.avtobys.driverboard.auth.data.network.AuthDataSource
import kz.avtobys.driverboard.auth.domain.model.AuthTokenResponseData
import kz.avtobys.driverboard.auth.domain.repository.IAuthRepository

class AuthRepository(
    private val authDataSource: AuthDataSource,
    private val mapper: AuthRefreshTokenApiModelMapper,
    private val securityLocalCache: SecurityLocalCache,
): IAuthRepository {

    override suspend fun getAccessToken(
        plateNumber: String,
        username: String,
        password: String,
    ): AuthTokenResponseData? {
        val response = authDataSource.getAccessToken(
            basicToken = BuildConfig.CLIENT_SECRET,
            grantType = GRANT_TYPE_PASSWORD,
            userName = createLogin(plateNumber),
            password = createPassword(plateNumber),
        )
        securityLocalCache.setAccessToken(response.body()?.accessToken.orEmpty())
        securityLocalCache.setRefreshToken(response.body()?.refreshToken.orEmpty())
        securityLocalCache.setTokenType(response.body()?.tokenType.orEmpty())
        securityLocalCache.setBusNumber(plateNumber)
        return response.body()?.let { mapper.map(it) }
    }

    private fun createLogin(plateNumber: String): String {
        return "S1$plateNumber"
    }

    private fun createPassword(plateNumber: String): String {
        return "S" + plateNumber.substring(0, 4) +
                BuildConfig.PASS_SECRET +
                plateNumber.substring(4)
    }
}
