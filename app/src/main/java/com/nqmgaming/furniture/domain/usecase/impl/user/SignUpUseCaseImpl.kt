package com.nqmgaming.furniture.domain.usecase.impl.user

import com.nqmgaming.furniture.domain.repository.AuthenticationRepository
import com.nqmgaming.furniture.domain.usecase.user.SignUpUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository

) : SignUpUseCase {
    override suspend fun execute(input: SignUpUseCase.Input): SignUpUseCase.Output {
        return withContext(Dispatchers.IO) {
            val result = authenticationRepository.signUp(input.email, input.password, input.name)
            if (result) {
                SignUpUseCase.Output.Success
            } else {
                SignUpUseCase.Output.Failure
            }
        }
    }
}