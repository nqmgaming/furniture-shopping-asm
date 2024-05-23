package com.nqmgaming.furniture.presentation.main.favorite

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nqmgaming.furniture.domain.mapper.asDomainModel
import com.nqmgaming.furniture.domain.mapper.asDtoModel
import com.nqmgaming.furniture.domain.model.favorite.Favorite
import com.nqmgaming.furniture.domain.model.product.Product
import com.nqmgaming.furniture.domain.usecase.GetFavoritesUseCase
import com.nqmgaming.furniture.domain.usecase.GetProductByIdUseCase
import com.nqmgaming.furniture.domain.usecase.UpdateFavoritesUseCase
import com.nqmgaming.furniture.util.SharedPrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val updateFavoritesUseCase: UpdateFavoritesUseCase,
    application: Application
) : AndroidViewModel(application) {

    private val _favoriteList = MutableStateFlow<List<Product>>(emptyList())
    val favoriteList = _favoriteList

    private val _favoritesId = MutableStateFlow(Favorite(emptyList()))

    private val userId = SharedPrefUtils.getInt(getApplication(), "userId", 0)

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    fun fetchFavoriteList() {
        _favoriteList.value = emptyList()
        viewModelScope.launch {
            getFavoriteList()
            Log.d("FavoriteScreen", "Log run in here")
            getProductById(_favoritesId.value.favoriteList)
            Log.d("FavoriteScreen", "FavoriteList: $favoriteList")
        }
    }


    fun onDeletedFavorite(product: Product) {
        viewModelScope.launch {
            deleteFavorite(product)
        }
    }

    private suspend fun deleteFavorite(product: Product) {
        Log.d("FavoriteScreen", "Product Id: ${product.productId}")
        val favoriteList = _favoritesId.value.favoriteList.toMutableList()
        Log.d("FavoriteScreen", "FavoriteList before delete: $favoriteList")
        favoriteList.removeIf { it == product.productId.toString() }
        Log.d("FavoriteScreen", "FavoriteList after delete: $favoriteList")
        val favorite = Favorite(favoriteList)
        Log.d("FavoriteScreen", "Favorite: $favorite")
        updateFavoriteList(favorite)
        _favoritesId.value = favorite
        _favoriteList.value = _favoriteList.value.toMutableList().also { products ->
            products.removeIf { it.productId == product.productId }
        }
        Log.d("FavoriteScreen", "FavoriteList after update: $favoriteList")
    }

    private suspend fun getFavoriteList() {
        val result = getFavoritesUseCase.execute(GetFavoritesUseCase.Input(userId))
        if (result is GetFavoritesUseCase.Output.Success) {
            _favoritesId.value = result.favorites.asDomainModel()
        }
    }

    private suspend fun getProductById(listProductId: List<String>) {

        for (productId in listProductId) {
            val result =
                getProductByIdUseCase.execute(GetProductByIdUseCase.Input(productId.toInt()))
            if (result is GetProductByIdUseCase.Output.Success) {
                _favoriteList.value += result.product.asDomainModel()
            }
        }
    }

    private suspend fun updateFavoriteList(favorite: Favorite) {
        updateFavoritesUseCase.execute(
            UpdateFavoritesUseCase.Input(
                userId,
                favorite.asDtoModel()
            )
        )
    }
}