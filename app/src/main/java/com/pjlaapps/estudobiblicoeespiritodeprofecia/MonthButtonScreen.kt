package com.pjlaapps.estudobiblicoeespiritodeprofecia

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pjlaapps.botoescomlista.getMesPorSigla
import com.pjlaapps.estudobiblicoeespiritodeprofecia.ui.theme.EstudoBiblicoEEspiritoDeProfeciaTheme


// criar tela com doze botões, cada um com o nome de um mês do ano
@Composable
fun MonthButtonsScreen(
    dataStoreManager: DataStoreManager,
    onItemClick: (String) -> Unit
) {

    //todo usar datastore e armazenar o mes de referencia
    //todo cada botao do mes a ser clicado deve ser passado ao dia de estudo
    //todo quando todos os dias ficarem verdes, o mes também vai pra verde
    //todo colocar botão de resetar também aqui

    Column {
        Text(
            "Clique em um mês para ver os estudos disponíveis",
        )
        LazyColumn {
            fun LinhaMeses(lista: List<String>) {
                item() {
                    Row {
                        // Lista de meses do ano
                        val months = lista

                        // Cria um botão para cada mês
                        months.forEach { month ->
                            val nomeMes = getMesPorSigla(month)
                            Button(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .weight(1f),
                                onClick = { onItemClick(month) },
                            ) {
                                Text(text = nomeMes)
                            }
                        }
                    }
                }
            }

            LinhaMeses(listOf(
                "JAN", "FEV", "MAR"
            ))
            LinhaMeses(listOf(
                "ABR", "MAI", "JUN"
            ))
            LinhaMeses(listOf(
                "JUL", "AGO", "SET"
            ))
            LinhaMeses(listOf(
                "OUT", "NOV", "DEZ"
            ))



        }
    }


}

@Preview(showBackground = true)
@Composable
fun MonthButtonsScreenPreview( context: Context = LocalContext.current) {
    val dataStoreManager = DataStoreManager(context)
    EstudoBiblicoEEspiritoDeProfeciaTheme {
//        var currentScreen by remember { mutableStateOf("main") }
//        var selectedMonthIndex by remember { mutableStateOf("JAN") }
        MonthButtonsScreen(
            dataStoreManager,
            onItemClick = {
//                selectedMonthIndex = it
//                currentScreen = "days"
            }
        )
    }
}