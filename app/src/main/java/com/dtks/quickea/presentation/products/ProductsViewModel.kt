package com.dtks.quickea.presentation.products

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dtks.quickea.di.DefaultDispatcher
import com.dtks.quickea.domain.model.Product
import com.dtks.quickea.domain.usecase.AddProductToCart
import com.dtks.quickea.domain.usecase.GetProducts
import com.dtks.quickea.domain.usecase.LoadProducts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    getProducts: GetProducts,
    private val loadProducts: LoadProducts,
    private val addProductToCart: AddProductToCart,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val allProducts = getProducts.invoke()
        .flowOn(dispatcher)
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
    private val _productAddState = MutableStateFlow(ProductAddState.AVAILABLE)
    val productAddState = _productAddState.asStateFlow()

    fun getProducts(context: Context) {
        viewModelScope.launch(dispatcher) {
            loadProducts.invoke(context)
        }
    }

    fun addProductToCart(product: Product) {
        viewModelScope.launch(dispatcher) {
            _productAddState.value = ProductAddState.ADDING
            addProductToCart.invoke(product)
            _productAddState.value = ProductAddState.AVAILABLE
        }
    }

}