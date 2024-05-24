package com.nqmgaming.furniture.domain.usecase.impl.cart

import com.nqmgaming.furniture.data.repository.CartRepository
import com.nqmgaming.furniture.domain.usecase.cart.AddCartUseCase
import javax.inject.Inject

class AddCartUseCaseImpl @Inject constructor(
    private val cartRepository: CartRepository
) : AddCartUseCase {
    override suspend fun execute(input: AddCartUseCase.Input): AddCartUseCase.Output {
        return try {
            val result = cartRepository.addToCart(
                input.productId,
                input.quantity,
                input.colorString,
                input.userId
            )
            AddCartUseCase.Output.Success(result)
        } catch (e: Exception) {
            AddCartUseCase.Output.Error
        }
    }
}