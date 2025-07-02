package com.pjlaapps.estudobiblicoeespiritodeprofecia

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BibleStudyScreen(referenciaBiblia: String, onBack: () -> Unit){
    Column {
        Text("EM CONSTRUCAO")
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = onBack) {
            Text("Voltar")
        }
    }
}