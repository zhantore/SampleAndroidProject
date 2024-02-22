package kz.avtobys.driverboard.di

import kz.avtobys.driverboard.auth.data.network.AuthDataSource
import org.koin.dsl.module
import retrofit2.Retrofit

val dataSourceModule = module {

    single { get<Retrofit>().create(AuthDataSource::class.java) }
}