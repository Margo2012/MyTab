package com.example.mytab

import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mytab.data.DataObject

@Composable
fun MultiSlider2(dataObject: DataObject) {
    // Обновление состояний в зависимости от положения виджета и характеристик
    if (dataObject.characteristic2) {
        // Виджет2 включен
        Text(text = "Характеристика1 не настраивается")
    } else {
        // Виджет2 выключен
        Slider(
            value = dataObject.characteristic1,
            steps = 201,
            valueRange = -10f..10f,
            onValueChange = { newValue ->
                dataObject.characteristic1 = newValue
            }
        )
    }
}
