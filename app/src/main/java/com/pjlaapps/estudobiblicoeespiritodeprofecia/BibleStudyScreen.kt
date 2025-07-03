package com.pjlaapps.estudobiblicoeespiritodeprofecia

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BibleStudyScreen(referenciaBiblia: String, onBack: () -> Unit){
    Column {
        Text("EM CONSTRUCAO")
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = onBack) {
            Text("Voltar")
        }
        
    }
}

@Preview
@Composable
fun BibleStudyScreenPreview() {
    BibleStudyScreen(referenciaBiblia = "Gênesis 1:1", onBack = {})
}