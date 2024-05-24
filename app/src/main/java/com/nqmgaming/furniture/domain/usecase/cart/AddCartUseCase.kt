package com.nqmgaming.furniture.domain.usecase.cart

import com.nqmgaming.furniture.data.network.dto.CartDto
import com.nqmgaming.furniture.domain.usecase.UseCase

interface AddCartUseCase : UseCase<AddCartUseCase.Input, AddCartUseCase.Output> {
    data class Input(
        val userId: Int,
        val productId: Int,
        val quantity: Int,
        val colorString: String
    )

    sealed class Output {
        data class Success(val cart: CartDto?) : Output()
        data object Error : Output()
    }
}