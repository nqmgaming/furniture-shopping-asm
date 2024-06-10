package com.nqmgaming.furniture.domain.usecase.order

import com.nqmgaming.furniture.data.network.dto.OrderDto
import com.nqmgaming.furniture.domain.usecase.UseCase

interface CreateOrderUseCase : UseCase<CreateOrderUseCase.Input, CreateOrderUseCase.Output> {
    data class Input(
        val orderDto: OrderDto
    )

    data class Output(
        val added: Boolean
    )
}