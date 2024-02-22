package kz.avtobys.driverboard.di

import kz.avtobys.driverboard.auth.presentation.ui.UnauthorizedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        UnauthorizedViewModel(
            repository = get(),
            securityDataSource = get(),
        )
    }
}