package kz.avtobys.driverboard

import android.app.Application
import kz.avtobys.driverboard.di.appModule
import kz.avtobys.driverboard.di.dataSourceModule
import kz.avtobys.driverboard.di.mapperModule
import kz.avtobys.driverboard.di.networkModule
import kz.avtobys.driverboard.di.persistenceModule
import kz.avtobys.driverboard.di.repositoryModule
import kz.avtobys.driverboard.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoinModules()
    }

    private fun initKoinModules() {
        val koinModulesList = arrayListOf(
            appModule,
            networkModule,
            persistenceModule,
            mapperModule,
            repositoryModule,
            dataSourceModule,
            viewModelModule,
        )
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(koinModulesList)
        }
    }
}