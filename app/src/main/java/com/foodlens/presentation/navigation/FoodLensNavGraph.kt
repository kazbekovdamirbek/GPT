package com.foodlens.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.foodlens.presentation.home.HomeScreen
import com.foodlens.presentation.home.HomeViewModel
import com.foodlens.presentation.onboarding.OnboardingScreen
import com.foodlens.presentation.onboarding.OnboardingViewModel

@Composable
fun FoodLensNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.ONBOARDING) {
        composable(Routes.ONBOARDING) {
            val vm: OnboardingViewModel = hiltViewModel()
            vm.setOnComplete {
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.ONBOARDING) { inclusive = true }
                }
            }
            OnboardingScreen(
                state = vm.state,
                onAction = vm::onAction
            )
        }

        composable(Routes.HOME) {
            val vm: HomeViewModel = hiltViewModel()
            HomeScreen(state = vm.state)
        }
    }
}
