package com.nqmgaming.furniture.data.mapper

import com.nqmgaming.furniture.data.network.dto.OrderDto
import com.nqmgaming.furniture.domain.model.order.Order

fun Order.asDtoModel() = OrderDto(
    orderDate = orderDate,
    quantity = quantity,
    total = total,
    status = status,
    userId = userId,
    productId = productId
)

fun OrderDto.asDomainModel() = Order(
    orderId = orderId,
    orderDate = orderDate,
    quantity = quantity,
    total = total,
    status = status,
    userId = userId,
    productId = productId
)