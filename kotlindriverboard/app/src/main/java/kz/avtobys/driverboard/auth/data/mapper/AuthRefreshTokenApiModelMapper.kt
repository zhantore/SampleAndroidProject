package kz.avtobys.driverboard.auth.data.mapper

import kz.avtobys.core.domain.mapper.Mapper
import kz.avtobys.driverboard.auth.data.model.AuthTokenResponse
import kz.avtobys.driverboard.auth.domain.model.AuthTokenResponseData

class AuthRefreshTokenApiModelMapper : Mapper<AuthTokenResponse, AuthTokenResponseData>() {

    override fun map(from: AuthTokenResponse): AuthTokenResponseData =
        AuthTokenResponseData(
            accessToken = from.accessToken,
            refreshToken = from.refreshToken,
            tokenType = from.tokenType,
            jti = from.jti,
        )
}