package com.nqmgaming.furniture.domain.usecase

interface DeleteFavoriteByIdUseCase :
    UseCase<DeleteFavoriteByIdUseCase.Input, DeleteFavoriteByIdUseCase.Output> {
    class Input(val favoriteId: Int)
    sealed class Output() {
        data class Success(val data: Unit) : Output()
        data object Failure : Output()
    }
}