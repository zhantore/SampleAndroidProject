package kz.avtobys.driverboard.auth.data.repository

import kz.avtobys.driverboard.auth.data.cache.SecurityLocalCache
import kz.avtobys.driverboard.auth.domain.repository.IRefreshTokenRepository

class RefreshTokenRepository(
    private val securityLocalCache: SecurityLocalCache,
): IRefreshTokenRepository {

    override fun getRefreshTokenRepository(): String {
        return securityLocalCache.getRefreshToken().orEmpty()
    }
}