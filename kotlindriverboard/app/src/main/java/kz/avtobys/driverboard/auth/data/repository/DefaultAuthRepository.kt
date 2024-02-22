package kz.avtobys.driverboard.auth.data.repository

import kz.avtobys.common.utils.constants.ApiConstant.GRANT_TYPE_PASSWORD
import kz.avtobys.driverboard.BuildConfig
import kz.avtobys.driverboard.auth.data.mapper.AuthRefreshTokenApiModelMapper
import kz.avtobys.driverboard.auth.data.network.AuthDataSource
import kz.avtobys.driverboard.auth.domain.model.AuthRefreshTokenData
import kz.avtobys.driverboard.auth.domain.repository.AuthRepository

class DefaultAuthRepository(
    private val authDataSource: AuthDataSource,
    private val mapper: AuthRefreshTokenApiModelMapper,
): AuthRepository {

    override suspend fun getAccessToken(
        plateNumber: String,
        username: String,
        password: String,
    ): AuthRefreshTokenData? {
        val response = authDataSource.getAccessToken(
            basicToken = BuildConfig.CLIENT_SECRET,
            grantType = GRANT_TYPE_PASSWORD,
            userName = createLogin(plateNumber),
            password = createPassword(plateNumber),
        )
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
