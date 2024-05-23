package com.nqmgaming.furniture.domain.usecase

interface AddFavoriteUseCase : UseCase<AddFavoriteUseCase.Input, AddFavoriteUseCase.Output> {
    class Input(
        val userId: Int,
        val productId: Int
    )
    sealed class Output {
        data class Success(val data: Unit) : Output()
        data object Failure : Output()
    }

}