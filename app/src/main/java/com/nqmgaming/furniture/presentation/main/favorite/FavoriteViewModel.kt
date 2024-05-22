package com.nqmgaming.furniture.presentation.main.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nqmgaming.furniture.data.network.dto.FavoriteDto
import com.nqmgaming.furniture.domain.usecase.GetFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteUseCase: GetFavoriteUseCase
) : ViewModel() {

    private val _favoriteList = MutableStateFlow<List<FavoriteDto>?>(listOf())
    val favoriteList: Flow<List<FavoriteDto>?> = _favoriteList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading


    suspend fun getFavorites(userId: Int) {
        _isLoading.value = true
        try {
            viewModelScope.launch {
                val result = getFavoriteUseCase.execute(GetFavoriteUseCase.Input(userId))
                when (result) {
                    is GetFavoriteUseCase.Output.Success -> {
                        _favoriteList.emit(result.data)
                    }

                    is GetFavoriteUseCase.Output.Failure -> {
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