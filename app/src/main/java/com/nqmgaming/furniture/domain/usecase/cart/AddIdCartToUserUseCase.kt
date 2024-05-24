package com.nqmgaming.furniture.domain.usecase.cart

import com.nqmgaming.furniture.domain.usecase.UseCase

interface AddIdCartToUserUseCase :
    UseCase<AddIdCartToUserUseCase.Input, AddIdCartToUserUseCase.Output> {
    data class Input(
        val userId: Int,
        val cartsId: List<String>
    )

    sealed class Output {
        data object Success : Output()
        data object Error : Output()
    }
}