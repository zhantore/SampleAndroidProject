package kz.avtobys.driverboard.auth.data.repository

import kz.avtobys.driverboard.auth.data.cache.SecurityLocalCache
import kz.avtobys.driverboard.auth.domain.repository.AccessTokenRepository

class DefaultAccessTokenRepository(
    private val securityLocalCache: SecurityLocalCache,
): AccessTokenRepository {

    override fun getAccessTokenRepository(): String {
        return securityLocalCache.getAccessToken().orEmpty()
    }
}