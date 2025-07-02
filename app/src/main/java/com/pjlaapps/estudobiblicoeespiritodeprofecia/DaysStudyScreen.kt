package com.pjlaapps.estudobiblicoeespiritodeprofecia

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pjlaapps.estudobiblicoeespiritodeprofecia.ui.theme.EstudoBiblicoEEspiritoDeProfeciaTheme
import kotlinx.coroutines.launch

@Composable
fun DaysStudyScreen(
    dataStoreManager: DataStoreManager,
    onItemClick: (Int) -> Unit
) {
    val mes = arrayOf("JAN","FEV","MAR","ABR","MAI","JUN","JUL","AGO","SET","OUT","NOV","DEZ");
    val nomemes = arrayOf("JANEIRO","FEVEREIRO","MARÇO","ABRIL","MAIO","JUNHO",
        "JULHO","AGOSTO","SETEMBRO","OUTUBRO","NOVEMBRO","DEZEMBRO");
    val qmes = intArrayOf(31,28,31,30,31,30,31,31,30,31,30,31);
    val scope = rememberCoroutineScope()
    //todo o numero do estado varia com o mes que deve vir por parametro
    val states by dataStoreManager.getButtonStates().collectAsState(initial = List(30) { 0 })

    Column(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = { scope.launch { dataStoreManager.resetAll() } },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("Resetar Todos")
        }

        LazyColumn {

            items(30) { index ->
                val color = when (states[index]) {
                    3 -> Color.Green
                    2 -> Color.Yellow
                    1 -> Color.Yellow
                    else -> Color.Red
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .height(50.dp)
                        .background(color)
                        .clickable { onItemClick(index) },
                    contentAlignment = Alignment.Center
                ) {
                    Text("Item ${index + 1}", color = Color.White)
                }

            }

            }

    }
}

@Preview(showBackground = true)
@Composable
fun DaysStudyScreenPreview(
    context: Context = LocalContext.current
) {
    val dataStoreManager = DataStoreManager(context)

    EstudoBiblicoEEspiritoDeProfeciaTheme {
        var currentScreen by remember { mutableStateOf("main") }
        var selectedIndex by remember { mutableStateOf(-1) }
        DaysStudyScreen(
            dataStoreManager,
            onItemClick = {
            selectedIndex = it
            currentScreen = "detail"
        } );
    }
}