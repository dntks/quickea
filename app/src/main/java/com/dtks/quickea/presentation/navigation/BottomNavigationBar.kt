package com.dtks.quickea.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dtks.quickea.domain.model.CartItem
import com.dtks.quickea.presentation.cart.CartScreen
import com.dtks.quickea.presentation.cart.CartViewModel
import com.dtks.quickea.presentation.products.ProductsScreen

@Composable
fun BottomNavigationBar(
) {
    var navigationSelectedItem by remember {
        mutableStateOf(0)
    }
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                bottomNavigationItems()
                    .forEachIndexed { index, navigationItem ->

                        //iterating all items with their respective indexes
                        NavigationBarItem(
                            selected = index == navigationSelectedItem,
                            label = {
                                Text(stringResource(id = navigationItem.labelId))
                            },
                            icon = {
                                NavigationBarIcon(navigationItem)
                            },
                            onClick = {
                                navigationSelectedItem = index
                                navController.navigate(navigationItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Product.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable(Screen.Product.route) {
                ProductsScreen()
            }
            composable(Screen.Cart.route) {
                CartScreen()
            }
        }
    }
}

@Composable
private fun NavigationBarIcon(
    navigationItem: BottomNavigationItem,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val countOfItems by cartViewModel.countOfItems.collectAsStateWithLifecycle()
    when (navigationItem.route) {
        Screen.Cart.route -> {
            countOfItems?.let{
                BadgedBox(badge = {
                    Badge {
                        Text(text = it)
                    }
                }) {
                    NavigationIcon(navigationItem)
                }
            } ?: NavigationIcon(navigationItem)
        }

        else -> NavigationIcon(navigationItem)
    }
}

@Composable
private fun NavigationIcon(navigationItem: BottomNavigationItem) {
    Icon(
        navigationItem.icon,
        contentDescription = stringResource(id = navigationItem.labelId)
    )
}