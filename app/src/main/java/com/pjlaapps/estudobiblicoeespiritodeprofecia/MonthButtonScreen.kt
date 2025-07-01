package com.pjlaapps.estudobiblicoeespiritodeprofecia

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pjlaapps.estudobiblicoeespiritodeprofecia.ui.theme.EstudoBiblicoEEspiritoDeProfeciaTheme


// criar tela com doze botões, cada um com o nome de um mês do ano
@Composable
fun MonthButtonsScreen() {
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
                            Button(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .weight(1f),
                                onClick = { /* Ação ao clicar no botão */ }
                            ) {
                                Text(text = month)
                            }
                        }
                    }
                }
            }

            LinhaMeses(listOf(
                "Janeiro", "Fevereiro", "Março"
            ))
            LinhaMeses(listOf(
                "Abril", "Maio", "Junho"
            ))
            LinhaMeses(listOf(
                "Julho", "Agosto", "Setembro"
            ))
            LinhaMeses(listOf(
                "Outubro", "Novembro", "Dezembro"
            ))



        }
    }


}

@Preview(showBackground = true)
@Composable
fun MonthButtonsScreenPreview() {
    EstudoBiblicoEEspiritoDeProfeciaTheme {
        MonthButtonsScreen()
    }
}