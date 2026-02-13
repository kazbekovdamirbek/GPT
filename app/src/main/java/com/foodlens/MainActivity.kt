package com.foodlens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.foodlens.presentation.navigation.FoodLensNavGraph
import com.foodlens.ui.theme.FoodLensTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodLensTheme {
                FoodLensNavGraph()
            }
        }
    }
}
