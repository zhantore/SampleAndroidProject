package kz.avtobys.driverboard.di

import kz.avtobys.driverboard.auth.data.repository.DefaultAuthRepository
import kz.avtobys.driverboard.auth.domain.repository.AuthRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<AuthRepository> {
        DefaultAuthRepository(
            authDataSource = get(),
            mapper = get(),
        )
    }
}