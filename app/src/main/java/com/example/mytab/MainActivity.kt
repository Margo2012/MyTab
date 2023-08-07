package com.example.mytab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mytab.ui.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen()
        }
    }
}


//@ExperimentalPagerApi
//@Composable
//fun TabLayout(
//) {
//    var tabIndex by remember { mutableStateOf(0) }
//    val tabs = listOf(
//        "Tab1", "Tab2"
//    )
//
//    Column(modifier = Modifier.fillMaxWidth()) {
//        TabRow(
//            selectedTabIndex = tabIndex,
//            contentColor = Color.White
//        ) {
//            tabs.forEachIndexed { index, title ->
//                Tab(selected = tabIndex == index,
//                    onClick = { tabIndex = index },
//                    text = { Text(title, color = Color.White, fontSize = 24.sp) }
//
//                )
//            }
//        }
//
//        when(tabIndex){
//            0 -> Screen1()
//            1 -> Screen2()
//        }
//
//    }




