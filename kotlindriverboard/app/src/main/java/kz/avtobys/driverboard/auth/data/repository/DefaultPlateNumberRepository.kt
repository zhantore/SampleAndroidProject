package kz.avtobys.driverboard.auth.data.repository

import kz.avtobys.driverboard.auth.data.cache.SecurityLocalCache
import kz.avtobys.driverboard.auth.domain.repository.PlateNumberRepository

class DefaultPlateNumberRepository(
    private val securityLocalCache: SecurityLocalCache,
): PlateNumberRepository {

    override fun getPlateNumber(): String {
        return securityLocalCache.getBusNumber().orEmpty()
    }
}