package com.nqmgaming.furniture.domain.usecase.impl.cart

import com.nqmgaming.furniture.domain.repository.CartRepository
import com.nqmgaming.furniture.domain.usecase.cart.DecrementQuantityCartUseCase
import javax.inject.Inject

class DecrementQuantityCartUseCaseImpl @Inject constructor(
    private val cartRepository: CartRepository
) : DecrementQuantityCartUseCase {
    override suspend fun execute(input: DecrementQuantityCartUseCase.Input): DecrementQuantityCartUseCase.Output {
        return try {
            val quantity = cartRepository.decreaseCartItem(input.cartId, input.quantity)
            DecrementQuantityCartUseCase.Output.Success
        } catch (e: Exception) {
            DecrementQuantityCartUseCase.Output.Error
        }
    }
}