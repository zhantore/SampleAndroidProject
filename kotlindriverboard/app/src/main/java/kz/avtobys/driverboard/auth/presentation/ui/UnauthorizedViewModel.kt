package kz.avtobys.driverboard.auth.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kz.avtobys.common.utils.ext.empty
import kz.avtobys.common.utils.ext.isNotNull
import kz.avtobys.core.presentation.ext.launchSafe
import kz.avtobys.driverboard.auth.data.network.SecurityDataSource
import kz.avtobys.driverboard.auth.domain.repository.AuthRepository

class UnauthorizedViewModel(
    private val repository: AuthRepository,
    private val securityDataSource: SecurityDataSource,
): ViewModel() {

    private var _plateNumber = MutableStateFlow(String.empty)
    val plateNumber = _plateNumber.asStateFlow()

    init {
        onGetPlateNumber()
    }

    fun onLoginClick(userName: String, password: String, plateNumber: String) {
        viewModelScope.launchSafe(
             onError = {}
        ) {
            val response = repository.getAccessToken(
                plateNumber = plateNumber,
                username = userName,
                password = password,
            )
            if (response?.accessToken.isNotNull()) {
                securityDataSource.setAccessToken(response?.accessToken.orEmpty())
                securityDataSource.setRefreshToken(response?.refreshToken.orEmpty())
                securityDataSource.setTokenType(response?.tokenType.orEmpty())
                securityDataSource.setBusNumber(plateNumber)
            }
        }
    }

    private fun onGetPlateNumber() {
        _plateNumber.value = securityDataSource.getBusNumber().orEmpty()
    }
}