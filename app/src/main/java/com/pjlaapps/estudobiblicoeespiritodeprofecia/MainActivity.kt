package com.pjlaapps.estudobiblicoeespiritodeprofecia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                            onItemClick = {
                                selectedIndex = it
                                currentScreen = "detail"
                            }
                        )
                    }
                    "detail" -> {
                        DetailStudyScreen(dataStoreManager = dataStoreManager,
                            index = selectedIndex,
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
