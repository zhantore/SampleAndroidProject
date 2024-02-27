package kz.avtobys.driverboard.auth.data.repository

import kz.avtobys.driverboard.auth.data.cache.SecurityLocalCache
import kz.avtobys.driverboard.auth.domain.repository.IPlateNumberRepository

class PlateNumberRepository(
    private val securityLocalCache: SecurityLocalCache,
): IPlateNumberRepository {

    override fun getPlateNumber(): String {
        return securityLocalCache.getBusNumber().orEmpty()
    }
}