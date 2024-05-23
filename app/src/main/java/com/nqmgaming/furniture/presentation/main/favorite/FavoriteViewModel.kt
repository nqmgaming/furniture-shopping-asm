package com.nqmgaming.furniture.presentation.main.favorite

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
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
import io.ktor.client.engine.android.Android
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
    val favoritesId = _favoritesId

    private val userId = SharedPrefUtils.getInt(getApplication(), "userId", 0)

    init {

    }

    fun fetchFavoriteList() {
        _favoriteList.value = emptyList()
        viewModelScope.launch {
            getFavoriteList()
            Log.d("FavoriteScreen", "Log run in here")
            getProductById(favoritesId.value.favoriteList)
            Log.d("FavoriteScreen", "FavoriteList: $favoriteList")
        }
    }

    fun onDeleteFavoriteClick(product: Product) {
        val favoriteList = favoritesId.value.favoriteList.toMutableList()
        favoriteList.remove(product.productId.toString())
        val favorite = Favorite(favoriteList)
        viewModelScope.launch {
            updateFavoriteList(favorite)
            _favoriteList.value = _favoriteList.value.toMutableList().also {
                it.remove(product)
            }
            Toast.makeText(getApplication(), "Product removed from favorite", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private suspend fun getFavoriteList() {
        val result = getFavoritesUseCase.execute(GetFavoritesUseCase.Input(userId))
        if (result is GetFavoritesUseCase.Output.Success) {
            Log.d("FavoriteViewModel", "Success")
            _favoritesId.value = result.favorites.asDomainModel()
            Log.d("FavoriteViewModel", "Success")
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

    private suspend fun updateFavoriteList(favoriteList: Favorite) {
        val result = updateFavoritesUseCase.execute(
            UpdateFavoritesUseCase.Input(
                userId,
                favoriteList.asDtoModel()
            )
        )
        if (result is UpdateFavoritesUseCase.Output.Success) {
            Log.d("FavoriteViewModel", "Success")
        }
    }


}