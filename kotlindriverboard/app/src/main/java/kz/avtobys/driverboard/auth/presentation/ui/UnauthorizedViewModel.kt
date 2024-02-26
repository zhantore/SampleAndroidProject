package kz.avtobys.driverboard.auth.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kz.avtobys.core.presentation.ext.empty
import kz.avtobys.core.presentation.ext.launchSafe
import kz.avtobys.driverboard.auth.domain.repository.AuthRepository
import kz.avtobys.driverboard.auth.domain.repository.PlateNumberRepository

class UnauthorizedViewModel(
    private val authRepository: AuthRepository,
    private val plateNumberRepository: PlateNumberRepository,
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
            val response = authRepository.getAccessToken(
                plateNumber = plateNumber,
                username = userName,
                password = password,
            )
        }
    }

    private fun onGetPlateNumber() {
        _plateNumber.value = plateNumberRepository.getPlateNumber()
    }
}