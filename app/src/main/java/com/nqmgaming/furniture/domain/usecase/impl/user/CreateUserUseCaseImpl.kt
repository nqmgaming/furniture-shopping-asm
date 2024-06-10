package com.nqmgaming.furniture.domain.usecase.impl.user

import com.nqmgaming.furniture.domain.repository.AuthenticationRepository
import com.nqmgaming.furniture.domain.usecase.user.CreateUserUseCase
import javax.inject.Inject

class CreateUserUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : CreateUserUseCase {
    override suspend fun execute(input: CreateUserUseCase.Input): CreateUserUseCase.Output {
        return try {
            val user = authenticationRepository.insertUser(input.email, input.name)
            CreateUserUseCase.Output.Success
        } catch (e: Exception) {
            e.printStackTrace()
            CreateUserUseCase.Output.Error(e.message ?: "An error occurred")
        }
    }
}