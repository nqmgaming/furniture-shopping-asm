package com.nqmgaming.furniture.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto (
    @SerialName("user_id")
    val userId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
)