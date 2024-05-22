package com.nqmgaming.furniture.domain.usecase

interface LogInUseCase : UseCase<LogInUseCase.Input, LogInUseCase.Output> {
    class Input(val email: String, val password: String)
    sealed class Output() {
        data object Success : Output()
        data object Failure : Output()
    }
}