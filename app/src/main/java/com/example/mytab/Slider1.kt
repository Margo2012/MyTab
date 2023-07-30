package com.example.mytab

import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import com.example.mytab.data.DataObject

@Composable
fun MultiSlider1(dataObject: DataObject) {

    if (dataObject.characteristic2) {
        // Характеристика1 неактивна
        Slider(
            value = dataObject.characteristic3,
            steps = 201,
            valueRange = -10f..10f,
            onValueChange = { newValue ->
                dataObject.characteristic3 = newValue
            }
        )

        Slider(
            value = dataObject.characteristic4,
            steps = 11,
            valueRange = 0f..10f,
            onValueChange = { newValue ->
                dataObject.characteristic4 = newValue
            }
        )

        Slider(
            value = dataObject.characteristic5,
            steps = 11,
            valueRange = 0f..10f,
            onValueChange = { newValue ->
                dataObject.characteristic5 = newValue
            }
        )
    } else {
        // Характеристика1 активна
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
