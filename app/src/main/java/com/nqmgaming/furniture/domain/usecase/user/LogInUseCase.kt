package com.nqmgaming.furniture.domain.usecase.user

import com.nqmgaming.furniture.domain.usecase.UseCase

interface LogInUseCase : UseCase<LogInUseCase.Input, LogInUseCase.Output> {
    class Input(val email: String, val password: String)
    sealed class Output() {
        data object Success : Output()
        data object Failure : Output()
    }
}