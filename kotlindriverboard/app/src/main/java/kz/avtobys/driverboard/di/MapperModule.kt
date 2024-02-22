package kz.avtobys.driverboard.di

import kz.avtobys.driverboard.auth.data.mapper.AuthRefreshTokenApiModelMapper
import org.koin.dsl.module

val mapperModule = module {

    factory {
        AuthRefreshTokenApiModelMapper()
    }
}