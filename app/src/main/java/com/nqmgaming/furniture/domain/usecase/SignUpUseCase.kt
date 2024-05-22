package com.nqmgaming.furniture.domain.usecase

interface SignUpUseCase: UseCase<SignUpUseCase.Input, SignUpUseCase.Output> {
    class Input(val email: String, val password: String, val name: String)
    sealed class Output {
        data object Success: Output()
        data object Failure: Output()
    }
}