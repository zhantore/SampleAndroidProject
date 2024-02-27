package kz.avtobys.driverboard.auth.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kz.avtobys.core.presentation.ext.empty
import kz.avtobys.core.presentation.ext.launchSafe
import kz.avtobys.driverboard.auth.domain.repository.IAuthRepository
import kz.avtobys.driverboard.auth.domain.repository.IPlateNumberRepository

class UnauthorizedViewModel(
    private val IAuthRepository: IAuthRepository,
    private val IPlateNumberRepository: IPlateNumberRepository,
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
            val response = IAuthRepository.getAccessToken(
                plateNumber = plateNumber,
                username = userName,
                password = password,
            )
        }
    }

    private fun onGetPlateNumber() {
        _plateNumber.value = IPlateNumberRepository.getPlateNumber()
    }
}