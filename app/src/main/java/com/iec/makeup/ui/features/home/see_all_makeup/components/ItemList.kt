package com.iec.makeup.ui.features.home.see_all_makeup.components

import androidx.compose.runtime.Composable
import com.iec.makeup.core.model.MakeUpStylist
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.iec.makeup.ui.theme.ColorDB7093
import com.iec.makeup.ui.theme.ColorFFE4E1

@Composable
fun HorizontalItemCard(
    item: MakeUpStylist,
    onBookNowClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image
            AsyncImage(
                model = item.imageUrl,
                contentDescription = "image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                // Title
                Text(
                    text = item.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Description with order info
                Text(
                    text = "${item.monthlyOrder} orders/month",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                // Rating
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = item.rating.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Book Now Button
            Box(
                modifier = Modifier
                    .background(
                        ColorDB7093,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .clickable(onClick = onBookNowClick)
            ) {
                Text(
                    text = "Book Now",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HorizontalItemCard(
        item = MakeUpStylist(
            id = 1.toString(),
            name = "MakeUp Stylist 1",
            imageUrl = "https://images.unsplash.com/photo-1534528741775-53994a69daeb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=580&q=80",
            priceType = "Hourly",
            price = 20.0,
            detail = "Makeup artist with a wide range of experience in various styles.",
            rating = 4.5,
            ratingCount = 100,
            location = "New York",
            isFavorite = false,
            isVerified = true,
            isOnline = true,
            isAvailable = true,
            monthlyOrder = 124,
        ),
        onBookNowClick = {}
    )
}