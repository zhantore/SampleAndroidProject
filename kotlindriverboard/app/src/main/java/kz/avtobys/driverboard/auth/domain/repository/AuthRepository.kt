package kz.avtobys.driverboard.auth.domain.repository

import kz.avtobys.driverboard.auth.domain.model.AuthRefreshTokenData

interface AuthRepository {

    suspend fun getAccessToken(
        plateNumber: String,
        username: String,
        password: String
    ): AuthRefreshTokenData?
}
