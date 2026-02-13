package com.foodlens.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(state: HomeUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Сегодня", style = MaterialTheme.typography.headlineSmall)

        state.target?.let { target ->
            Card(modifier = Modifier.padding(top = 8.dp)) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Калории: ${target.calories} ккал")
                    Text("Белки: ${target.protein} г")
                    Text("Жиры: ${target.fat} г")
                    Text("Углеводы: ${target.carbs} г")
                }
            }
        } ?: Text(state.emptyStateMessage)
    }
}
