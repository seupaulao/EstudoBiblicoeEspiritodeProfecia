package com.pjlaapps.estudobiblicoeespiritodeprofecia

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pjlaapps.botoescomlista.getMesPorSigla
import com.pjlaapps.botoescomlista.getNumeroDiasMes
import com.pjlaapps.estudobiblicoeespiritodeprofecia.ui.theme.EstudoBiblicoEEspiritoDeProfeciaTheme
import kotlinx.coroutines.launch

@Composable
fun DaysStudyScreen(
    dataStoreManager: DataStoreManager,
    mes: String = "JAN", // Default month, can be changed
    onItemClick: (Int, String) -> Unit
) {

    val scope = rememberCoroutineScope()
    val numeroDiasDoMes = getNumeroDiasMes(mes)
    val nomeMes = getMesPorSigla(mes)
    //todo o numero do estado varia com o mes que deve vir por parametro
    val states by dataStoreManager.getButtonStates().collectAsState(initial = List(numeroDiasDoMes) { 0 })

    Column(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = { scope.launch { dataStoreManager.resetAll() } },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("Resetar Todos")
        }


        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    nomeMes,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    style = LocalTextStyle.current.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
            }

            items(numeroDiasDoMes) { index ->
                val color = when (states[index]) {
                    3 -> Color.Green
                    2, 1 -> Color.Yellow
                    else -> Color.Red
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color)
                        .clickable { onItemClick(index, mes) },
                    contentAlignment = Alignment.Center
                ) {
                    Text("${index + 1}", color = Color.White)
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
        //var currentScreen by remember { mutableStateOf("main") }
       // var selectedIndex by remember { mutableStateOf(-1) }
        DaysStudyScreen(
            dataStoreManager,
            "FEV",
            onItemClick = { index, mes ->;
                //currentScreen = "detail"
                //selectedIndex = index
                // Handle item click here, e.g., navigate to detail screen
            })
    }
}