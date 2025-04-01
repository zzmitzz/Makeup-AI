package com.iec.makeup.ui.features.home.see_all_makeup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iec.makeup.core.model.mockListData
import com.iec.makeup.ui.features.home.see_all_makeup.components.HorizontalItemCard
import com.iec.makeup.ui.features.home.see_all_makeup.components.SearchBar


@Composable
fun AllMakeUpScreen(
    navBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            SearchBar(
                onBackClick = navBack
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier.padding(8.dp)
        ) {
            val fakeData = mockListData
            LazyColumn {
                items(fakeData.size) { index ->
                    val item = fakeData[index]
                    HorizontalItemCard(item = item, onBookNowClick = { /* Handle book now click */ })

                    Spacer(modifier = Modifier.height(8.dp))

                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AllMakeUpScreen()
}

