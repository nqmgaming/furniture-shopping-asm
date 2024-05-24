package com.nqmgaming.furniture.domain.usecase.cart

import com.nqmgaming.furniture.data.network.dto.CartDto
import com.nqmgaming.furniture.domain.usecase.UseCase

interface GetCartByIdUseCase : UseCase<GetCartByIdUseCase.Input, GetCartByIdUseCase.Output> {
    class Input(val cartId: String)
    sealed class Output() {
        data class Success(val cart: CartDto) : Output()
        data object Failure : Output()
    }
}