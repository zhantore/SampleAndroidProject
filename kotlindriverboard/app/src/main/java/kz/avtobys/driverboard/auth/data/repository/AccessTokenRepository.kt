package kz.avtobys.driverboard.auth.data.repository

import kz.avtobys.driverboard.auth.data.cache.SecurityLocalCache
import kz.avtobys.driverboard.auth.domain.repository.IAccessTokenRepository

class AccessTokenRepository(
    private val securityLocalCache: SecurityLocalCache,
): IAccessTokenRepository {

    override fun getAccessTokenRepository(): String {
        return securityLocalCache.getAccessToken().orEmpty()
    }
}