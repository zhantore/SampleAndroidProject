package kz.avtobys.driverboard.auth.data.repository

import kz.avtobys.driverboard.auth.data.cache.SecurityLocalCache
import kz.avtobys.driverboard.auth.domain.repository.RefreshTokenRepository

class DefaultRefreshTokenRepository(
    private val securityLocalCache: SecurityLocalCache,
): RefreshTokenRepository {

    override fun getRefreshTokenRepository(): String {
        return securityLocalCache.getRefreshToken().orEmpty()
    }
}