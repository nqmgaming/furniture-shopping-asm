package com.nqmgaming.furniture.domain.usecase.user

import com.nqmgaming.furniture.data.network.dto.UserDto
import com.nqmgaming.furniture.domain.usecase.UseCase

interface GetUserInfoUseCase : UseCase<GetUserInfoUseCase.Input, GetUserInfoUseCase.Output> {
    class Input(val email: String)
    sealed class Output() {
        data class Success(val user: UserDto) : Output()
        data object Failure : Output()
    }
}