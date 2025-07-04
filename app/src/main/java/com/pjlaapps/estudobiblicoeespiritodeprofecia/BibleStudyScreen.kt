package com.pjlaapps.estudobiblicoeespiritodeprofecia

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pjlaapps.botoescomlista.getTextoBiblicoFromJson
import com.pjlaapps.botoescomlista.getVersiculosPorReferencia

@Composable
fun BibleStudyScreen(referenciaBiblia: String, onBack: () -> Unit){
    val titulos = referenciaBiblia.split(",")
    val context = androidx.compose.ui.platform.LocalContext.current
    val textoteste = getTextoBiblicoFromJson(context, "GEN_1_1")
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Leitura da Biblia")
        Text("$textoteste")
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn {
            items(titulos.size) { index ->
                Text(
                    text = titulos[index],
                    modifier = Modifier.padding(8.dp)
                )
                val textos = getVersiculosPorReferencia(context, titulos[index])
                LazyColumn {
                    items(textos.size) {
                        subindex ->
                        Text(
                            text = "${subindex + 1}. ${textos[subindex]}",
                            modifier = Modifier.padding(start = 16.dp, end = 8.dp, bottom = 8.dp)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = onBack) {
            Text("Voltar")
        }
        
    }
}

@Preview
@Composable
fun BibleStudyScreenPreview() {
    BibleStudyScreen(referenciaBiblia = "GEN_1", onBack = {})
}