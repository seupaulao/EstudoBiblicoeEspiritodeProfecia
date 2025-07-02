package com.pjlaapps.estudobiblicoeespiritodeprofecia

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pjlaapps.estudobiblicoeespiritodeprofecia.ui.theme.EstudoBiblicoEEspiritoDeProfeciaTheme
import kotlinx.coroutines.launch

@Composable
fun DetailStudyScreen(
    dataStoreManager: DataStoreManager,
    index: Int,
    onBack: () -> Unit,
    onReadBible: (String) -> Unit
) {
    val scope = rememberCoroutineScope()
    var clickedA by remember { mutableStateOf(false) }
    var clickedB by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        dataStoreManager.getButtonStates().collect { states ->
            when (states[index]) {
                3 -> { clickedA = true; clickedB = true }
                2 -> { clickedA = true; clickedB = false }
                1 -> { clickedA = false; clickedB = true } // ou vice-versa
                0 -> { clickedA = false; clickedB = false }
            }
        }
    }

    fun updateState() {
        val newState = when {
            clickedA==true && clickedB==true -> 3
            clickedA==true && clickedB==false -> 2
            clickedA==false && clickedB==true -> 1
            else -> 0
        }
        scope.launch { dataStoreManager.saveButtonState(index, newState) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Item ${index + 1}", style = MaterialTheme.typography.headlineSmall)
        //algum botão aqui cuidara de abrir o BibleStudyScreen
        Button(
            onClick = {
                clickedA = !clickedA
                updateState()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(if (clickedA) Color.Green else Color.Red)
        ) {
            Text("Botão A", color = Color.White)
        }

        Button(
            onClick = {
                clickedB = !clickedB
                updateState()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(if (clickedB) Color.Green else Color.Red)
        ) {
            Text("Botão B", color = Color.White)
        }

        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = onBack) {
            Text("Voltar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailStudyScreenPreview(
    context: Context = LocalContext.current
) {
    val dataStoreManager = DataStoreManager(context)

        var currentScreen by remember { mutableStateOf("main") }
        var selectedIndex by remember { mutableStateOf(-1) }
        var referenciaBiblia by remember { mutableStateOf("GEN_1") }

    DetailStudyScreen(
            dataStoreManager,
            selectedIndex,
            onBack = {
                currentScreen = "main"
            },
            onReadBible =  {
                referenciaBiblia = "GEN_1"
                currentScreen = "bible"
            }
        );
}