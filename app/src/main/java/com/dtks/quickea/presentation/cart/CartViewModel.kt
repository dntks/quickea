package com.dtks.quickea.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dtks.quickea.di.DefaultDispatcher
import com.dtks.quickea.domain.model.CartItem
import com.dtks.quickea.domain.usecase.CartItemCount
import com.dtks.quickea.domain.usecase.GetCartItems
import com.dtks.quickea.domain.usecase.RemoveCartItem
import com.dtks.quickea.domain.usecase.SumCartItemPrices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel to get cart related data.
 */
@HiltViewModel
class CartViewModel @Inject constructor(
    getCartItems: GetCartItems,
    private val removeCartItem: RemoveCartItem,
    private val sumCartItemPrices: SumCartItemPrices,
    private val cartItemCount: CartItemCount,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val cartItems = getCartItems.invoke()
        .flowOn(dispatcher)
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
    val sumOfItems = cartItems.map { cartItems ->
        sumCartItemPrices.invoke(cartItems)
    }
        .flowOn(dispatcher)
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
    val countOfItems = cartItems.map { cartItems ->
        cartItemCount.invoke(cartItems)
    }
        .flowOn(dispatcher)
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    fun remove(cartItem: CartItem) {
        viewModelScope.launch(dispatcher) {
            removeCartItem.invoke(cartItem)
        }
    }
}