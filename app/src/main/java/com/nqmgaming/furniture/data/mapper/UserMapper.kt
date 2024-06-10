package com.nqmgaming.furniture.data.mapper

import com.nqmgaming.furniture.data.network.dto.UserDto
import com.nqmgaming.furniture.domain.model.user.User

fun UserDto.asDomainModel() = User(
    userId = userId,
    name = name,
    email = email,
)

fun User.asDtoModel() = UserDto(
    userId = userId,
    name = name,
    email = email,
)