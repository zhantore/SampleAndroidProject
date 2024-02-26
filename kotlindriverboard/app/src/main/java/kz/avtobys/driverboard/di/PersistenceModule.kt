package kz.avtobys.driverboard.di

import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import kz.avtobys.driverboard.auth.data.cache.SecurityLocalCache
import org.koin.dsl.module

val persistenceModule = module {

    single {
        SecurityLocalCache(pref = get<EncryptedSharedPreferences>())
    }
    single {
        val masterKey = MasterKey.Builder(get())
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        EncryptedSharedPreferences.create(
            get(),
            "secret_shared_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        ) as EncryptedSharedPreferences
    }
}