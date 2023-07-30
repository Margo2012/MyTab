package com.example.mytab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.RadioButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.mytab.data.DataObject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.max


@Composable
fun Screen(){
    //Cостояние обьекта
    val dataObject = remember {
        DataObject()
    }

    // Состояние выбранной вкладки
    var tabSelected by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        //Вкладки
        TabRow(selectedTabIndex = tabSelected, contentColor = MaterialTheme.colorScheme.primary) {
            Tab(
                selected = tabSelected == 0,
                onClick = {tabSelected = 0}
            ){
                Text(text = "Tab1")
            }
            Tab(selected = tabSelected == 1, 
                onClick = { tabSelected = 1 }) {
                Text(text = "Tab2")
            }
            
        }
        //Виджеты и мультислайдеры
        if (tabSelected == 0){
            Widget1(dataObject)
            MultiSlider1(dataObject)
        }else{
            Widget2(dataObject)
            MultiSlider2(dataObject)
        }
        
    }
}


@Composable
fun Widget1(dataObject: DataObject) {
    var widget1State by remember { mutableStateOf(0) }

    // Обработчик изменения состояния виджета1
    LaunchedEffect(widget1State) {
        // Логирование изменения состояния виджета1
       // LogCurrentState(dataObject)

        // Обновление состояний в зависимости от положения виджета1
        when (widget1State) {
            0 -> {
                // Выкл
                dataObject.characteristic2 = false
            }
            1 -> {
                // Положение 1
                dataObject.characteristic2 = true
                dataObject.characteristic3 = 0f
                dataObject.characteristic4 = 0f
                dataObject.characteristic5 = 0f
            }
            2 -> {
                // Положение 2
                dataObject.characteristic2 = true
                // Обновление Характеристики1 в зависимости от Характеристик 3 и 4
                dataObject.characteristic1 = max(dataObject.characteristic3, dataObject.characteristic4)
            }
        }
    }

    Column {
        RadioButton(
            selected = widget1State == 0,
            onClick = { widget1State = 0 }
        )
        androidx.compose.material.Text(text = "Выкл")

        RadioButton(
            selected = widget1State == 1,
            onClick = { widget1State = 1 }
        )
        androidx.compose.material.Text(text = "Положение 1")

        RadioButton(
            selected = widget1State == 2,
            onClick = { widget1State = 2 }
        )
        androidx.compose.material.Text(text = "Положение 2")
    }
}

@Composable
fun Widget2(dataObject: DataObject) {
    var widget2State by remember { mutableStateOf(0) }

    // Обработчик изменения состояния виджета2
    LaunchedEffect(widget2State) {
        // Логирование изменения состояния виджета2
        //LogCurrentState(dataObject)

        // Обновление состояний в зависимости от положения виджета2
        when (widget2State) {
            0 -> {
                // Выкл
                dataObject.characteristic1 = 0f
            }
            1 -> {
                // Положение 1
                dataObject.characteristic1 = max(dataObject.characteristic3, dataObject.characteristic4)
            }
        }
    }

    Column {
        // Виджет2
        RadioButton(
            selected = widget2State == 0,
            onClick = { widget2State = 0 }
        )
        Text(text = "Выкл")

        RadioButton(
            selected = widget2State == 1,
            onClick = { widget2State = 1 }
        )
        Text(text = "Положение 1")
    }
}



@Composable
fun LogCurrentState(dataObject: DataObject) {
    val context = LocalContext.current
    println("Текущее состояние:")
    println("Характеристика1: ${dataObject.characteristic1}")
    println("Характеристика2: ${dataObject.characteristic2}")
    println("Характеристика3: ${dataObject.characteristic3}")
    println("Характеристика4: ${dataObject.characteristic4}")
    println("Характеристика5: ${dataObject.characteristic5}")
    println()
}

@Preview
@Composable
fun PreviewScreen() {
    Screen()
}


