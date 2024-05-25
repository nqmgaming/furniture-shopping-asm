package com.nqmgaming.furniture.domain.usecase.user

import com.nqmgaming.furniture.data.network.dto.UserDto
import com.nqmgaming.furniture.domain.usecase.UseCase

interface CreateUserUseCase : UseCase<CreateUserUseCase.Input, CreateUserUseCase.Output> {
    data class Input(
        val email: String,
        val name: String
    )

    sealed class Output {
        data object Success : Output()
        data class Error(val message: String) : Output()
    }
}