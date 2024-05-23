package com.nqmgaming.furniture.presentation.main.productDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nqmgaming.furniture.data.repository.ProductRepository
import com.nqmgaming.furniture.domain.mapper.asDomainModel
import com.nqmgaming.furniture.domain.model.product.Product
import com.nqmgaming.furniture.domain.usecase.AddFavoriteUseCase
import com.nqmgaming.furniture.domain.usecase.DeleteFavoriteByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteByIdUseCase: DeleteFavoriteByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _product = MutableStateFlow<Product?>(null)
    val product = _product


    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    init {
        val productId = savedStateHandle.get<Int>("productId")
        viewModelScope.launch {
            val result = productRepository.getProductById(productId!!).asDomainModel()
            _product.emit(result)
        }
    }

    private suspend fun addFavorite(userId: Int, productId: Int) {
        _isLoading.value = true
        try {
            viewModelScope.launch {
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
                val result =
                    deleteFavoriteByIdUseCase.execute(DeleteFavoriteByIdUseCase.Input(favoriteId))
                when (result) {
                    is DeleteFavoriteByIdUseCase.Output.Success -> {
                        _isLoading.value = false
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
}