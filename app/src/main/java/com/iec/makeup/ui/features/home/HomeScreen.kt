package com.iec.makeup.ui.features.home


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iec.makeup.core.model.mockListData
import com.iec.makeup.ui.features.home.components.OrderStatusChips
import com.iec.makeup.ui.features.home.components.StoriesItems
import com.iec.makeup.ui.features.home.components.StunningRoundedCard
import com.iec.makeup.ui.features.home.components.TopAppBar
import com.iec.makeup.ui.features.home.helpers.OrderStatusType
import com.iec.makeup.ui.theme.ColorDB7093
import com.iec.makeup.ui.theme.ColorFF69B4
import com.iec.makeup.ui.theme.ColorFFC1CC



/*
 - Later implement of MVI architecture, just manage state directly on screen.
 */


@Composable
fun HomeScreen(
    navToNotification: () -> Unit = {},
    navToSearch: () -> Unit = {},
    navToAllMakeUp: () -> Unit = {}
) {
    val context = LocalContext.current
    AuraBeautyApp(
        navToNotification = navToNotification,
        navToSearch = navToSearch,
        navToAllMakeUp = navToAllMakeUp
    )
}


@Composable
fun AuraBeautyApp(
    navToNotification: () -> Unit = {},
    navToSearch: () -> Unit = {},
    navToAllMakeUp: () -> Unit = {}
) {
    val scrollview = rememberScrollState()
    val orderChipSelected = remember {mutableStateOf<OrderStatusType>(OrderStatusType.TO_RECEIVE)}
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(
                scrollview
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top Bar
            TopAppBar(
                showNotifications = navToNotification
            )
            // Content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                // Greeting card
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Hello, Juliet!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Search,
                        tint = ColorFF69B4,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            navToSearch()
                        }
                    )
                }
                // My Orders Section
                Text(
                    text = "Orders",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
                )
                // Order Status Chips
                OrderStatusChips(
                    viewToPay = {
                        orderChipSelected.value = OrderStatusType.TO_PAY
                    },
                    viewToReceive = {
                        orderChipSelected.value = OrderStatusType.TO_RECEIVE
                    },
                    viewToReview = {
                        orderChipSelected.value = OrderStatusType.TO_REVIEW
                    },
                    currentSelected = orderChipSelected.value
                )
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.elevatedCardElevation(0.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = ColorDB7093
                    )
                ) {
                   Box(
                       modifier = Modifier.fillMaxSize(),
                       contentAlignment = Alignment.Center
                   ){
                       when(orderChipSelected.value) {
                           OrderStatusType.TO_PAY -> {
                               Text(
                                   text = "You have 1 order to pay",
                                   fontWeight = FontWeight.Bold,
                                   color = Color.White,
                                   fontSize = 12.sp,
                                   modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
                               )
                           }
                           OrderStatusType.TO_RECEIVE -> {
                               Text(
                                   text = "You have 1 order to receive",
                                   fontWeight = FontWeight.Bold,
                                   color = Color.White,
                                   fontSize = 12.sp,
                                   modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
                               )
                           }
                           OrderStatusType.TO_REVIEW -> {
                               Text(
                                   text = "You have 1 order to review",
                                   fontWeight = FontWeight.Bold,
                                   color = Color.White,
                                   fontSize = 12.sp,
                                   modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
                               )
                           }
                       }
                   }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Reels",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
                    )
                    Text(
                        text = "See all >",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = ColorFF69B4,
                        modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
                    )
                }
                // Stories Items
                StoriesItems()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Booking",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
                    )
                    Text(
                        text = "See all >",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = ColorFF69B4,
                        modifier = Modifier.padding(top = 12.dp, bottom = 12.dp).clickable {
                            navToAllMakeUp()
                        }
                    )
                }
                val listMock = mockListData
                LazyRow {
                    items(listMock.size) { index ->
                        StunningRoundedCard(
                            imageURL = listMock[index].imageUrl,
                            title = listMock[index].name,
                            subtitle = listMock[index].detail,
                            buttonText = if (listMock[index].isAvailable) "Book Now" else "Unavailable",
                            onButtonClick = {
                                if (listMock[index].isAvailable) {
                                    // Handle book now action
                                }
                            }
                        )
                    }
                }
            }
        }

    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
    AuraBeautyApp()
}



