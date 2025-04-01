package com.iec.makeup.ui.features.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun TopAppBar(
    doScan: () -> Unit = {},
    showNotifications: () -> Unit = {},
    showChat: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Logo

        LogoComponent()
        // Right Icons
        Row {
            Icon(
                imageVector = Icons.Outlined.QrCodeScanner,
                contentDescription = "Compare",
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable {
                        doScan()
                    }
            )
            Icon(
                imageVector = Icons.Outlined.NotificationsNone,
                contentDescription = "Notifications",
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable {
                        showNotifications()
                    }
            )
            Icon(
                imageVector = Icons.Outlined.Chat,
                contentDescription = "Settings",
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable {
                        showChat()
                    }
            )
        }
    }
}