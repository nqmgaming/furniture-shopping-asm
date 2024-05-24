package com.nqmgaming.furniture.domain.usecase.cart

import com.nqmgaming.furniture.data.network.dto.CartDto
import com.nqmgaming.furniture.domain.usecase.UseCase

interface GetCartsByUserIdUseCase :
    UseCase<GetCartsByUserIdUseCase.Input, GetCartsByUserIdUseCase.Output> {
    data class Input(
        val userId: Int
    )
    sealed class Output {
        data class Success(val carts: List<CartDto>) : Output()
        data object Failure : Output()
    }
}