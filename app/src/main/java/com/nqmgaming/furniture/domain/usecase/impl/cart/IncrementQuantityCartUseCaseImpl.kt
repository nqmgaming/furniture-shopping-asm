package com.nqmgaming.furniture.domain.usecase.impl.cart

import com.nqmgaming.furniture.domain.repository.CartRepository
import com.nqmgaming.furniture.domain.usecase.cart.IncrementQuantityCartUseCase
import javax.inject.Inject

class IncrementQuantityCartUseCaseImpl @Inject constructor(
    private val cartRepository: CartRepository
): IncrementQuantityCartUseCase {
    override suspend fun execute(input: IncrementQuantityCartUseCase.Input): IncrementQuantityCartUseCase.Output {
        return try {
            val quantity = cartRepository.increaseCartItem(input.cartId, input.quantity)
            IncrementQuantityCartUseCase.Output.Success
        } catch (e: Exception) {
            IncrementQuantityCartUseCase.Output.Error
        }
    }
}