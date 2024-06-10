package com.nqmgaming.furniture.domain.usecase.impl.user

import com.nqmgaming.furniture.domain.repository.AuthenticationRepository
import com.nqmgaming.furniture.domain.usecase.user.GetUserInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserInfoUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : GetUserInfoUseCase {
    override suspend fun execute(input: GetUserInfoUseCase.Input): GetUserInfoUseCase.Output {

        return try {
            withContext(Dispatchers.IO) {
                val result = authenticationRepository.getInformationByEmail(input.email)
                if (result != null) {
                    GetUserInfoUseCase.Output.Success(result)
                } else {
                    GetUserInfoUseCase.Output.Failure
                }
            }
        } catch (
            e: Exception
        ) {
            e.printStackTrace()
            GetUserInfoUseCase.Output.Failure

        }

    }
}