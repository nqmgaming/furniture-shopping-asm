package com.nqmgaming.furniture.domain.usecase.product

import com.nqmgaming.furniture.data.network.dto.ProductDto
import com.nqmgaming.furniture.domain.usecase.UseCase

interface GetProductByIdUseCase :
    UseCase<GetProductByIdUseCase.Input, GetProductByIdUseCase.Output> {
    class Input(val productId: Int)
    sealed class Output() {
        data class Success(val product: ProductDto) : Output()
        data object Failure : Output()
    }
}