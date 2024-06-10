package com.nqmgaming.furniture.domain.usecase.impl.order

import android.util.Log
import com.nqmgaming.furniture.domain.repository.OrderRepository
import com.nqmgaming.furniture.domain.usecase.order.CreateOrderUseCase
import javax.inject.Inject

class CreateOrderUseCaseImpl @Inject constructor(
    private val orderRepository: OrderRepository
) : CreateOrderUseCase {
    override suspend fun execute(input: CreateOrderUseCase.Input): CreateOrderUseCase.Output {
        return try {
            val result = orderRepository.createOrder(
                input.orderDto
            )
            Log.d("CreateOrderUseCaseImpl", "result: ${input.orderDto}")
            CreateOrderUseCase.Output(
                added = result
            )
        } catch (e: Exception) {
            CreateOrderUseCase.Output(
                added = false
            )
        }
    }
}