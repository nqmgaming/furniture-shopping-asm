package com.nqmgaming.furniture.domain.usecase.cart

import com.nqmgaming.furniture.data.network.dto.CartDto
import com.nqmgaming.furniture.domain.usecase.UseCase

interface RemoveCartItemUseCase : UseCase<RemoveCartItemUseCase.Input, RemoveCartItemUseCase.Output> {
    data class Input(
        val cart: CartDto,
        val userId: Int,
        val cartsId: List<String>
    )

    sealed class Output {
        data object Success : Output()
        data object Error : Output()
    }
}