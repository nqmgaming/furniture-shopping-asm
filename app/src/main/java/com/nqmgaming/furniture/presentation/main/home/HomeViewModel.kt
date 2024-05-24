package com.nqmgaming.furniture.presentation.main.home

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nqmgaming.furniture.data.repository.ProductRepository
import com.nqmgaming.furniture.domain.mapper.asDomainModel
import com.nqmgaming.furniture.domain.model.cart.Cart
import com.nqmgaming.furniture.domain.model.product.Product
import com.nqmgaming.furniture.domain.usecase.cart.AddCartUseCase
import com.nqmgaming.furniture.domain.usecase.cart.GetCartByIdUseCase
import com.nqmgaming.furniture.domain.usecase.cart.GetCartsByUserIdUseCase
import com.nqmgaming.furniture.domain.usecase.cart.IncrementQuantityCartUseCase
import com.nqmgaming.furniture.domain.usecase.product.GetProductByIdUseCase
import com.nqmgaming.furniture.util.SharedPrefUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
) : ViewModel() {


    private val _productList = MutableStateFlow<List<Product>?>(listOf())
    val productList: Flow<List<Product>?> = _productList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            val products = productRepository.getProducts()
            _productList.emit(products.map {
                it.asDomainModel()
            })
        }
    }


}