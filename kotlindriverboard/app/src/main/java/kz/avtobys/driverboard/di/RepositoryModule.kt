package kz.avtobys.driverboard.di

import kz.avtobys.driverboard.auth.data.repository.AuthRepository
import kz.avtobys.driverboard.auth.data.repository.PlateNumberRepository
import kz.avtobys.driverboard.auth.domain.repository.IAuthRepository
import kz.avtobys.driverboard.auth.domain.repository.IPlateNumberRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<IAuthRepository> {
        AuthRepository(
            authDataSource = get(),
            mapper = get(),
            securityLocalCache = get(),
        )
    }
    factory<IPlateNumberRepository> {
        PlateNumberRepository(
            securityLocalCache = get(),
        )
    }
}