package kz.avtobys.driverboard.auth.data.mapper

import kz.avtobys.core.domain.mapper.Mapper
import kz.avtobys.driverboard.auth.data.model.AuthRefreshTokenApiModel
import kz.avtobys.driverboard.auth.domain.model.AuthRefreshTokenData

class AuthRefreshTokenApiModelMapper : Mapper<AuthRefreshTokenApiModel, AuthRefreshTokenData>() {

    override fun map(from: AuthRefreshTokenApiModel): AuthRefreshTokenData =
        AuthRefreshTokenData(
            accessToken = from.accessToken,
            refreshToken = from.refreshToken,
            tokenType = from.tokenType,
            jti = from.jti,
        )
}