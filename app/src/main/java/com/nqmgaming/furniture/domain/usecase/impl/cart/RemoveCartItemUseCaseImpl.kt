package com.nqmgaming.furniture.domain.usecase.impl.cart

import com.nqmgaming.furniture.data.repository.CartRepository
import com.nqmgaming.furniture.domain.usecase.cart.RemoveCartItemUseCase
import javax.inject.Inject

class RemoveCartItemUseCaseImpl @Inject constructor(
    private val cartRepository: CartRepository

) : RemoveCartItemUseCase {
    override suspend fun execute(input: RemoveCartItemUseCase.Input): RemoveCartItemUseCase.Output {
        return try {
            cartRepository.removeCartItem(input.cart, input.userId, input.cartsId)
            RemoveCartItemUseCase.Output.Success
        } catch (e: Exception) {
            RemoveCartItemUseCase.Output.Error
        }
    }
}