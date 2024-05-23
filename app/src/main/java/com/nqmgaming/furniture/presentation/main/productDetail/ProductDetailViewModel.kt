package com.nqmgaming.furniture.presentation.main.productDetail

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.nqmgaming.furniture.data.repository.ProductRepository
import com.nqmgaming.furniture.domain.mapper.asDomainModel
import com.nqmgaming.furniture.domain.model.product.Product
import com.nqmgaming.furniture.domain.usecase.AddFavoriteUseCase
import com.nqmgaming.furniture.domain.usecase.DeleteFavoriteByIdUseCase
import com.nqmgaming.furniture.domain.usecase.GetFavoriteById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.nqmgaming.furniture.util.SharedPrefUtils
import kotlinx.coroutines.delay

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteByIdUseCase: DeleteFavoriteByIdUseCase,
    private val getFavoriteById: GetFavoriteById,
    savedStateHandle: SavedStateHandle,
    application: Application
) : AndroidViewModel(application) {
    private var lastClickTime = 0L
    private var clickCount = 0
    private val _product = MutableStateFlow<Product?>(null)
    val product = _product

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: Flow<Boolean> = _isFavorite

    private val _favoriteId = MutableStateFlow(0)
    val favoriteId: Flow<Int> = _favoriteId


    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading
    private val userId = SharedPrefUtils.getInt(getApplication(), "userId", 0)

    init {
        val productId = savedStateHandle.get<Int>("productId")
        viewModelScope.launch {
            val result = productRepository.getProductById(productId!!).asDomainModel()
            _product.emit(result)
            getFavorite()
        }

    }

    fun onFavoriteClicked() {
        if (_isLoading.value) {
            return
        }
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime < 1000) { // 2 minutes
            if (clickCount >= 1) {
                // Show a message to the user that they are clicking too fast
                Toast.makeText(
                    getApplication(),
                    "You are clicking too fast. Please wait a moment.",
                    Toast.LENGTH_SHORT
                ).show()
                return
            } else {
                clickCount++
            }
        } else {
            lastClickTime = currentTime
            clickCount = 1
            viewModelScope.launch {
                if (userId != 0 && _product.value != null) {
                    if (_isFavorite.value) {
                        Log.d("ProductDetailViewModel", "onFavoriteClicked: deleteFavorite")
                        deleteFavorite(_favoriteId.value)
                        _isFavorite.value = false
                    } else if(!_isFavorite.value) {
                        Log.d("ProductDetailViewModel", "onFavoriteClicked: addFavorite")
                        addFavorite(userId, _product.value!!.productId)
                        getFavorite()
                        _isFavorite.value = true
                    }
                }
            }
        }

    }

    private suspend fun addFavorite(userId: Int, productId: Int) {
        _isLoading.value = true
        _isFavorite.value = true
        try {
            viewModelScope.launch {
                delay(500)
                val result = addFavoriteUseCase.execute(AddFavoriteUseCase.Input(userId, productId))
                when (result) {
                    is AddFavoriteUseCase.Output.Success -> {
                        Log.d("ProductDetailViewModel", "addFavorite: success")
                        _isLoading.value = false
                        _isFavorite.value = true
                    }

                    is AddFavoriteUseCase.Output.Failure -> {
                        Log.d("ProductDetailViewModel", "addFavorite: failed")
                        _isLoading.value = false
                        _isFavorite.value = true
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _isLoading.value = false
            _isFavorite.value = true
        }
    }

    private suspend fun deleteFavorite(favoriteId: Int) {
        _isLoading.value = true
        _isFavorite.value = false
        try {
            viewModelScope.launch {
                delay(500)
                val result =
                    deleteFavoriteByIdUseCase.execute(DeleteFavoriteByIdUseCase.Input(favoriteId))
                when (result) {
                    is DeleteFavoriteByIdUseCase.Output.Success -> {
                        _isLoading.value = false
                        _isFavorite.value = false
                        Log.d("ProductDetailViewModel", "deleteFavorite: success")
                    }

                    is DeleteFavoriteByIdUseCase.Output.Failure -> {
                        Log.d("ProductDetailViewModel", "deleteFavorite: failed")
                        _isLoading.value = false
                        _isFavorite.value = false
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _isLoading.value = false
            _isFavorite.value = false
        }
    }

    // Kotlin
    private fun getFavorite() {
        viewModelScope.launch {
            _isLoading.value = true
            val product = _product.value
            if (product != null) {
                delay(500)
                val result =
                    getFavoriteById.execute(GetFavoriteById.Input(userId, product.productId))
                when (result) {
                    is GetFavoriteById.Output.Success -> {
                        Log.d("ProductDetailViewModel", "getFavorite: success")
                        _favoriteId.value = result.favoriteRequest.favoriteId
                        _isFavorite.value = true
                    }

                    is GetFavoriteById.Output.Failure -> {
                        Log.d("ProductDetailViewModel", "getFavorite: failed")
                        _isFavorite.value = false
                        _isLoading.value = false
                    }
                }
            }
            _isLoading.value = false
        }
    }
}