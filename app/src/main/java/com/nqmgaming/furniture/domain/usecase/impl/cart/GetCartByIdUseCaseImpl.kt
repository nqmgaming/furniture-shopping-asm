package com.nqmgaming.furniture.domain.usecase.impl.cart

import com.nqmgaming.furniture.domain.repository.CartRepository
import com.nqmgaming.furniture.domain.usecase.cart.GetCartByIdUseCase
import javax.inject.Inject

class GetCartByIdUseCaseImpl @Inject constructor(
    private val cartRepository: CartRepository
) : GetCartByIdUseCase {
    override suspend fun execute(input: GetCartByIdUseCase.Input): GetCartByIdUseCase.Output {
        return try {
            val result = cartRepository.getCartItem(input.cartId)
            GetCartByIdUseCase.Output.Success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            GetCartByIdUseCase.Output.Failure
        }
    }
}