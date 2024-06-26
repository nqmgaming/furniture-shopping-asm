package com.nqmgaming.furniture.domain.usecase.impl.user

import com.nqmgaming.furniture.domain.repository.AuthenticationRepository
import com.nqmgaming.furniture.domain.usecase.user.LogInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LogInUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : LogInUseCase {
    override suspend fun execute(input: LogInUseCase.Input): LogInUseCase.Output {
        return withContext(Dispatchers.IO) {
            val result = authenticationRepository.signIn(input.email, input.password)
            if (result) {
                LogInUseCase.Output.Success
            } else {
                LogInUseCase.Output.Failure
            }
        }
    }
}