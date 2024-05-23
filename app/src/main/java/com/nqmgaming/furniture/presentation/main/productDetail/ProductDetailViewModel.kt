package com.nqmgaming.furniture.presentation.main.productDetail

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.nqmgaming.furniture.data.repository.ProductRepository
import com.nqmgaming.furniture.domain.mapper.asDomainModel
import com.nqmgaming.furniture.domain.mapper.asDtoModel
import com.nqmgaming.furniture.domain.model.favorite.Favorite
import com.nqmgaming.furniture.domain.model.product.Product
import com.nqmgaming.furniture.domain.usecase.GetFavoritesUseCase
import com.nqmgaming.furniture.domain.usecase.UpdateFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.nqmgaming.furniture.util.SharedPrefUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val updateFavoritesUseCase: UpdateFavoritesUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    savedStateHandle: SavedStateHandle,
    application: Application
) : AndroidViewModel(application) {

    val productId = savedStateHandle.get<Int>("productId")

    private val _favoriteList = MutableStateFlow<List<Product>>(emptyList())
    val favoriteList = _favoriteList

    private val _favoritesId = MutableStateFlow(Favorite(emptyList()))
    private val favoritesId = _favoritesId

    private val _product = MutableStateFlow<Product?>(null)
    val product = _product

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: Flow<Boolean> = _isFavorite

    private val mutex = Mutex()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading
    private val userId = SharedPrefUtils.getInt(getApplication(), "userId", 0)

    init {
        viewModelScope.launch {
            val result = productRepository.getProductById(productId!!).asDomainModel()
            _product.emit(result)
            getFavoriteList(userId)
        }

    }

    fun onFavoriteClick() {
        viewModelScope.launch {
            _isLoading.value = true
            mutex.withLock {
                _isFavorite.value = !_isFavorite.value
                if (_isFavorite.value) {
                    addFavorite(_product.value!!)
                    _isLoading.value = false
                } else {
                    deleteFavorite(_product.value!!)
                    _isLoading.value = false
                }
            }
        }
    }


    private suspend fun updateFavoriteList(favorite: Favorite) {
        val result = updateFavoritesUseCase.execute(
            UpdateFavoritesUseCase.Input(
                userId,
                favorite.asDtoModel()
            )
        )
        if (result is UpdateFavoritesUseCase.Output.Success) {
            Log.d("FavoriteViewModel", "Success")
        }
    }

    private suspend fun deleteFavorite(product: Product) {
        val favoriteList = favoritesId.value.favoriteList.toMutableList()
        favoriteList.remove(product.productId.toString())
        val favorite = Favorite(favoriteList)
        updateFavoriteList(favorite)
        _favoriteList.value = _favoriteList.value.toMutableList().also {
            it.remove(product)
        }
        Toast.makeText(getApplication(), "Product removed from favorite", Toast.LENGTH_SHORT)
            .show()
    }

    private suspend fun addFavorite(product: Product) {
        val favoriteList = favoritesId.value.favoriteList.toMutableList()
        favoriteList.add(product.productId.toString())
        val favorite = Favorite(favoriteList)
        updateFavoriteList(favorite)
        _favoriteList.value = _favoriteList.value.toMutableList().also {
            it.add(product)
        }
        Toast.makeText(getApplication(), "Product added to favorite", Toast.LENGTH_SHORT)
            .show()
    }


    private suspend fun getFavoriteList(userId: Int) {
        val result = getFavoritesUseCase.execute(GetFavoritesUseCase.Input(userId))
        if (result is GetFavoritesUseCase.Output.Success) {
            Log.d("FavoriteViewModel", "Success")
            _favoritesId.value = result.favorites.asDomainModel()
            if (_product.value != null) {
                _isFavorite.value =
                    _favoritesId.value.favoriteList.contains(_product.value!!.productId.toString())
            }
        }
    }
}