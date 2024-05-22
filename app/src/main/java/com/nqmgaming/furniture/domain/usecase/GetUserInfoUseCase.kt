package com.nqmgaming.furniture.domain.usecase

import com.nqmgaming.furniture.data.network.dto.UserDto

interface GetUserInfoUseCase : UseCase<GetUserInfoUseCase.Input, GetUserInfoUseCase.Output> {
    class Input(val email: String)
    sealed class Output() {
        data class Success(val user: UserDto) : Output()
        data object Failure : Output()
    }
}