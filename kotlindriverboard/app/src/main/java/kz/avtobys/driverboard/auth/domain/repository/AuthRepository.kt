package kz.avtobys.driverboard.auth.domain.repository

import kz.avtobys.driverboard.auth.domain.model.AuthTokenResponseData

interface AuthRepository {

    suspend fun getAccessToken(
        plateNumber: String,
        username: String,
        password: String
    ): AuthTokenResponseData?
}
