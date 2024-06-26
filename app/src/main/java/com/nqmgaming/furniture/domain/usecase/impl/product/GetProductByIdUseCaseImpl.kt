package com.nqmgaming.furniture.domain.usecase.impl.product

import com.nqmgaming.furniture.domain.repository.ProductRepository
import com.nqmgaming.furniture.domain.usecase.product.GetProductByIdUseCase
import javax.inject.Inject

class GetProductByIdUseCaseImpl @Inject constructor(
    private val productRepository: ProductRepository
): GetProductByIdUseCase {
    override suspend fun execute(input: GetProductByIdUseCase.Input): GetProductByIdUseCase.Output {
        return try {
            val result = productRepository.getProductById(input.productId)
            GetProductByIdUseCase.Output.Success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            GetProductByIdUseCase.Output.Failure
        }
    }
}