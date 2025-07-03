package com.pjlaapps.estudobiblicoeespiritodeprofecia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.pjlaapps.estudobiblicoeespiritodeprofecia.ui.theme.EstudoBiblicoEEspiritoDeProfeciaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStoreManager = DataStoreManager(applicationContext)
        enableEdgeToEdge()
        setContent {
            EstudoBiblicoEEspiritoDeProfeciaTheme {
                var currentScreen by remember { mutableStateOf("main") }
                var selectedIndex by remember { mutableStateOf(-1) }
                var selectedMonthIndex by remember { mutableStateOf("JAN") }
                var referenciaBiblia by remember { mutableStateOf("GEN_1") }

                when(currentScreen) {
                    "main" -> {
                        MonthButtonsScreen(
                            dataStoreManager = dataStoreManager,
                            onItemClick = {
                                selectedMonthIndex = it
                                currentScreen = "days"
                            }
                        )
                    }
                    "days" -> {
                        DaysStudyScreen(
                            dataStoreManager = dataStoreManager,
                            mes = selectedMonthIndex,
                            onItemClick = { it, mes ->
                                selectedIndex = it
                                selectedMonthIndex = mes
                                currentScreen = "detail"
                            }
                        )
                    }
                    "detail" -> {
                        DetailStudyScreen(dataStoreManager = dataStoreManager,
                            index = selectedIndex,
                            mes = selectedMonthIndex,
                            onBack = {
                                currentScreen = "main"
                            },
                            onReadBible = {
                                referenciaBiblia = it
                                currentScreen = "bible"
                            }
                        )
                    }
                    "bible" -> {
                        BibleStudyScreen(
                            referenciaBiblia = referenciaBiblia,
                            onBack = {
                                currentScreen = "main"
                            }
                        )
                    }
                }


            }
        }
    }
}
