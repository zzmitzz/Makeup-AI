package com.iec.makeup.ui.features.home.screen_all_makeup_template

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.iec.makeup.core.model.ui.MakeUpTemplateLayout
import com.iec.makeup.core.model.ui.mockMakeUpTemplateLayout
import com.iec.makeup.ui.theme.ColorDB7093


@Composable
fun ScreenAllMakeupTemplate(
    navBack: () -> Unit = {},
    title: String = "Layout Dự tiệc",
    navToTemplateDetail: (String) -> Unit = {},
    data: List<MakeUpTemplateLayout>
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            ColorDB7093,
                            Color.White
                        )
                    ),
                )
                .padding(16.dp)
        ){
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.size(24.dp).clickable {
                    navBack()
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FilterButton(
                        text = "Tất cả",
                        selected = true,
                        modifier = Modifier.weight(1f)
                    )
                    FilterButton(
                        text = "Yêu thích",
                        selected = false,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            items(data.size) { index ->
                PhotoCard(
                    image = data[index].image,
                    isFavorite = data[index].isFavorite,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun FilterButton(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier.padding(vertical = 12.dp)
    ) {
        Box(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        ){
            Text(
                text = text,
                color = if (selected) Color(0xFFFF6B78) else Color.Gray,
                fontWeight = if(selected) FontWeight.Bold else FontWeight.Normal,
            )
        }
    }
}

@Composable
fun PhotoCard(
    image: String,
    isFavorite: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .height(180.dp)
    ) {
        // Replace with your actual image resource
        AsyncImage(
            model = image,
            contentDescription = "Photo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Favorite Icon
        Box(
            modifier = Modifier
                .padding(8.dp).clip(CircleShape)
                .background(color = Color.White)
                .align(Alignment.TopEnd),
            contentAlignment = Alignment.Center
        ){
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color.Red,
                modifier = Modifier
                    .padding(2.dp)
                    .size(24.dp)
            )
        }
    }
}

@Preview
@Composable
fun RoundedCardPreview() {
    ScreenAllMakeupTemplate(
        data = mockMakeUpTemplateLayout
    )
}