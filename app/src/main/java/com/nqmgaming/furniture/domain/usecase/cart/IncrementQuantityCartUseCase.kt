package com.nqmgaming.furniture.domain.usecase.cart

import com.nqmgaming.furniture.domain.usecase.UseCase

interface IncrementQuantityCartUseCase :
    UseCase<IncrementQuantityCartUseCase.Input, IncrementQuantityCartUseCase.Output> {
    data class Input(
        val cartId: String,
        val quantity: Int
    )

    sealed class Output {
        data object Success : Output()
        data object Error : Output()
    }
}