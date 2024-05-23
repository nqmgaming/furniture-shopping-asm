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
            Log.d("ProductDetailViewModel", "onFavoriteClicked: ${_isFavorite.value}")
            viewModelScope.launch {
                if (userId != 0) {
                    if (_isFavorite.value) {
                        deleteFavorite(_favoriteId.value)
                        _isFavorite.value = false
                        Toast.makeText(getApplication(), "Removed from favorite", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        addFavorite(userId, _product.value!!.productId)
                        getFavorite()
                        _isFavorite.value = true
                        Toast.makeText(getApplication(), "Added to favorite", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

    }

    private suspend fun addFavorite(userId: Int, productId: Int) {
        _isLoading.value = true
        try {
            viewModelScope.launch {
                delay(500)
                val result = addFavoriteUseCase.execute(AddFavoriteUseCase.Input(userId, productId))
                when (result) {
                    is AddFavoriteUseCase.Output.Success -> {
                        _isLoading.value = false
                    }

                    is AddFavoriteUseCase.Output.Failure -> {
                        _isLoading.value = false
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _isLoading.value = false
        }
    }

    private suspend fun deleteFavorite(favoriteId: Int) {
        _isLoading.value = true
        try {
            viewModelScope.launch {
                delay(500)
                val result =
                    deleteFavoriteByIdUseCase.execute(DeleteFavoriteByIdUseCase.Input(favoriteId))
                when (result) {
                    is DeleteFavoriteByIdUseCase.Output.Success -> {
                        _isLoading.value = false
                        _isFavorite.value = false
                    }

                    is DeleteFavoriteByIdUseCase.Output.Failure -> {
                        _isLoading.value = false
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _isLoading.value = false
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
                        _favoriteId.value = result.favoriteRequest.favoriteId
                        _isFavorite.value = true
                        _isLoading.value = false
                    }

                    is GetFavoriteById.Output.Failure -> {
                        _isFavorite.value = false
                        _isLoading.value = false
                    }
                }
            }
            _isLoading.value = false
        }
    }
}