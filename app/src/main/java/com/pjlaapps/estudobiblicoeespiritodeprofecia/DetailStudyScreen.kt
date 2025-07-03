package com.pjlaapps.estudobiblicoeespiritodeprofecia

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pjlaapps.botoescomlista.getCapituloESPorReferencia
import com.pjlaapps.botoescomlista.getCapituloLeituraPorReferencia
import com.pjlaapps.botoescomlista.getRefsPorReferencia
import com.pjlaapps.botoescomlista.getSiglaESPorReferencia
import com.pjlaapps.botoescomlista.getTituloCapituloESPorReferencia
import com.pjlaapps.botoescomlista.getTituloLivroPorSigla
import com.pjlaapps.botoescomlista.getUrlPorSigla
import kotlinx.coroutines.launch


@Composable
fun DetailStudyScreen(
    dataStoreManager: DataStoreManager,
    index: Int,
    mes: String,
    onBack: () -> Unit,
    onReadBible: (String) -> Unit
) {
    val scope = rememberCoroutineScope()
    var clickedA by remember { mutableStateOf(false) }
    var clickedB by remember { mutableStateOf(false) }
    //todo capturar a referencia da biblia que deve ser lida atraves do indice do dia de estudo
    val chave = mes + "_" + (index + 1).toString()
    val refsBiblia = getRefsPorReferencia(chave)
    val capitulosBiblia = getCapituloLeituraPorReferencia(chave)
    val capitulosEP = getCapituloESPorReferencia(chave)
    val siglaEP = getSiglaESPorReferencia(chave)
    val tituloEP = getTituloCapituloESPorReferencia(chave)
    val tituloLivroEP = getTituloLivroPorSigla(siglaEP)
    val urlLivroEP = getUrlPorSigla(siglaEP)

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
        Button(onClick = { onReadBible(refsBiblia) }) {
            Text("Ir na Biblia")
        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = onBack) {
            Text("Voltar")
        }
    }
}

class FakeDataStoreManager : DataStoreManager(null) {
    override fun getButtonStates() = kotlinx.coroutines.flow.flowOf(List(31) { 0 })
    override suspend fun saveButtonState(index: Int, state: Int) { /* no-op */ }
}

@Preview(showBackground = true)
@Composable
fun DetailStudyScreenPreview(

) {
    val fakeDataStoreManager = remember { FakeDataStoreManager() }




        var selectedIndex by remember { mutableIntStateOf(-1) }
        var mesIndex by remember { mutableStateOf("JAN") }


    DetailStudyScreen(
            dataStoreManager = fakeDataStoreManager,
            selectedIndex,
            mesIndex,
            onBack = { },
            onReadBible =  { }
        );
}