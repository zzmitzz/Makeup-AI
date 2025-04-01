package com.iec.makeup.ui.features.ai_makeup

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext


@Composable
fun DetailScreen(
    navBack: () -> Unit = {},
    navInstruction: () -> Unit = {}
) {
    val context = LocalContext.current
    Text(
        modifier = Modifier.clickable {
            navInstruction()
        },
        text = "Instruction"
    )
}