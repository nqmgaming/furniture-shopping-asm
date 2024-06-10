package com.nqmgaming.furniture.domain.usecase.impl.cart

import com.nqmgaming.furniture.domain.repository.CartRepository
import com.nqmgaming.furniture.domain.usecase.cart.GetCartsByUserIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCartsByUserIdUseCaseImpl @Inject constructor(
    private val cartRepository: CartRepository
) : GetCartsByUserIdUseCase {
    override suspend fun execute(input: GetCartsByUserIdUseCase.Input): GetCartsByUserIdUseCase.Output {
        return try {
            withContext(Dispatchers.IO) {
                val result = cartRepository.getCartsByUserId(input.userId)
                GetCartsByUserIdUseCase.Output.Success(result)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            GetCartsByUserIdUseCase.Output.Failure
        }
    }
}