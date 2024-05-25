package com.nqmgaming.furniture.data.repository

import com.nqmgaming.furniture.data.network.dto.UserDto

interface AuthenticationRepository {
    suspend fun signIn(email: String, password: String): Boolean
    suspend fun getInformationByEmail(email: String): UserDto
    suspend fun signUp(email: String, password: String, name: String): Boolean
    suspend fun insertUser( email: String, name: String)
}