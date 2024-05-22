package com.nqmgaming.furniture.domain.model.user

data class User(
    val userId: Int,
    val name: String,
    val email: String,
    val avatar: String? = null,
)
