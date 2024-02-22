package kz.avtobys.driverboard.auth.data.network

import android.content.SharedPreferences
import androidx.core.content.edit
import kz.avtobys.common.utils.ext.empty


private const val PREF_AUTH_ACCESS_TOKEN = "PREF_AUTH_ACCESS_TOKEN"
private const val PREF_AUTH_REFRESH_TOKEN = "PREF_AUTH_REFRESH_TOKEN"
private const val PREF_USER_TOKEN_TYPE = "PREF_USER_TOKEN_TYPE"
private const val BUS_NUMBER = "BUS_NUMBER"


class SecurityDataSource(
    private val pref: SharedPreferences,
) {

    /**
     * Метод для logout пользователем
     */
    fun clearAuthorizedUserData() {
        pref.edit { remove(PREF_AUTH_REFRESH_TOKEN) }
        pref.edit { remove(PREF_AUTH_ACCESS_TOKEN) }
        pref.edit { remove(PREF_USER_TOKEN_TYPE) }
        pref.edit { remove(BUS_NUMBER) }
    }

    /**
     * Сохраняем номер автобуса
     */
    fun setBusNumber(busNumber: String?) = pref.edit { putString(BUS_NUMBER, busNumber) }

    /**
     * Получаем номер автобуса
     */
    fun getBusNumber() = pref.getString(BUS_NUMBER, String.empty)

    /**
     * Сохраняем auth access token
     */
    fun setAccessToken(token: String) = pref.edit { putString(PREF_AUTH_ACCESS_TOKEN, token) }

    /**
     * Получаем auth access token
     */
    fun getAccessToken() = pref.getString(PREF_AUTH_ACCESS_TOKEN, String.empty)

    /**
     * Сохраняем auth refresh token
     */
    fun setRefreshToken(token: String) = pref.edit { putString(PREF_AUTH_REFRESH_TOKEN, token) }

    /**
     * Получаем auth refresh token
     */
    fun getRefreshToken() = pref.getString(PREF_AUTH_REFRESH_TOKEN, String.empty)

    /**
     * Получаем token type
     */
    fun getTokenType() = pref.getString(PREF_USER_TOKEN_TYPE, String.empty)

    /**
     * Сохраняем token type
     */
    fun setTokenType(tokenType: String) = pref.edit { putString(PREF_USER_TOKEN_TYPE, tokenType) }
}