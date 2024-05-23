package com.nqmgaming.furniture.presentation.main.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nqmgaming.furniture.domain.mapper.asDomainModel
import com.nqmgaming.furniture.domain.model.favorite.Favorite
import com.nqmgaming.furniture.domain.usecase.DeleteFavoriteByIdUseCase
import com.nqmgaming.furniture.domain.usecase.GetFavoritesUseCase
import com.nqmgaming.furniture.util.SharedPrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val deleteFavoriteByIdUseCase: DeleteFavoriteByIdUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val _favoriteList = MutableStateFlow<List<Favorite>?>(listOf())
    val favoriteList: Flow<List<Favorite>?> = _favoriteList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading
    private val userId = SharedPrefUtils.getInt(getApplication(), "userId", 0)

    suspend fun getFavorites() {
        _isLoading.value = true
        try {
            viewModelScope.launch {
                val result = getFavoritesUseCase.execute(GetFavoritesUseCase.Input(userId))
                when (result) {
                    is GetFavoritesUseCase.Output.Success -> {
                        _favoriteList.emit(result.data.map {
                            it.asDomainModel()
                        })
                    }

                    is GetFavoritesUseCase.Output.Failure -> {
                        _favoriteList.emit(listOf())
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _favoriteList.emit(listOf())
        }
        _isLoading.value = false

    }


    fun onDeletedFavorite(favoriteId: Int) {
        viewModelScope.launch {
            deleteFavorite(favoriteId)
        }
    }

    private suspend fun deleteFavorite(favoriteId: Int) {
        _isLoading.value = true
        try {
            viewModelScope.launch {
                val result =
                    deleteFavoriteByIdUseCase.execute(DeleteFavoriteByIdUseCase.Input(favoriteId))
                when (result) {
                    is DeleteFavoriteByIdUseCase.Output.Success -> {
                        getFavorites()
                    }

                    is DeleteFavoriteByIdUseCase.Output.Failure -> {
                        _favoriteList.emit(listOf())
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _favoriteList.emit(listOf())
        }
        _isLoading.value = false
    }

}