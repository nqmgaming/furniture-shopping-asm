package com.nqmgaming.furniture.domain.usecase.impl.cart

import com.nqmgaming.furniture.data.repository.CartRepository
import com.nqmgaming.furniture.domain.usecase.cart.AddIdCartToUserUseCase
import javax.inject.Inject

class AddIdCartToUserUseCaseImpl @Inject constructor(
    private val cartRepository: CartRepository
) : AddIdCartToUserUseCase {
    override suspend fun execute(input: AddIdCartToUserUseCase.Input): AddIdCartToUserUseCase.Output {
        return try {
            cartRepository.addIdCart(input.userId, input.cartsId)
            AddIdCartToUserUseCase.Output.Success
        } catch (e: Exception) {
            AddIdCartToUserUseCase.Output.Error
        }
    }
}