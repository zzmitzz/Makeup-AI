package com.iec.makeup.ui.features.ai_makeup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iec.makeup.R
import com.iec.makeup.ui.features.ai_makeup.components.ManualScreenComponents
import com.iec.makeup.ui.theme.ColorFF69B4
import kotlinx.coroutines.launch


/*
 - Just a state less screen, show instruction, not include business logic
 */



enum class InstructionDetailEnu(
    val image: Int,
    val title: String,
    val description: String
) {
    Screen1(
        image = R.drawable.instruction_1_2,
        title = "Step 1",
        description = "Please click on the camera icon to open your camera"
    ),
    Screen2(
        image = R.drawable.instruction_1_2,
        title = "Step 2",
        description = "Please click on the camera icon to open your camera"
    ),
    Screen3(
        image = R.drawable.instruction_1_2,
        title = "Step 3",
        description = "Please click on the camera icon to open your camera"
    )
}

@Preview(showBackground = true)
@Composable
private fun Preiview() {

    InstructionScreen()

}

@Composable
fun InstructionScreen(
    navBack: () -> Unit = {},
    navLaunchScreen: () -> Unit = {}
) {
    val context = LocalContext.current
    val coroutines = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 3 }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .background(Color.White)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBackIosNew,
            contentDescription = "Back",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    navBack()
                }
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            HorizontalPager(
                modifier = Modifier.fillMaxWidth(),
                state = pagerState
            ) { page ->
                ManualScreenComponents(
                    imageInstruction = InstructionDetailEnu.entries[page].image,
                    textInstruction = InstructionDetailEnu.entries[page].title,
                    detailInstruction = InstructionDetailEnu.entries[page].description
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        if (pagerState.currentPage == 2) {
                            navLaunchScreen()
                        } else {
                            coroutines.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = ColorFF69B4,
                        backgroundColor = ColorFF69B4
                    )
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = if (pagerState.currentPage == 2) "Understand" else "Next",
                        color = Color.White
                    )
                }
            }
        }
    }
}