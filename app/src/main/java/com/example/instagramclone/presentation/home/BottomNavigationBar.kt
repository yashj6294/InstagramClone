package com.example.instagramclone.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.instagramclone.util.Screen

enum class BottomNavItems(val icon: ImageVector, val screen: Screen) {
    HOME(Icons.Default.Home, Screen.HomeScreen),
    SEARCH(Icons.Default.Search, Screen.SearchScreen),
    PROFILE(
        Icons.Default.Person,
        Screen.ProfileScreen
    ),
}

@Composable
fun BottomNavigationBar(navHostController: NavHostController, selectedItem: BottomNavItems) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
    ) {
        for (item in BottomNavItems.values()) {
            Image(
                imageVector = item.icon,
                contentDescription = item.name,
                modifier = Modifier
                    .size(40.dp)
                    .weight(1f)
                    .padding(5.dp)
                    .clickable {
                        navHostController.navigate(item.screen.route) {
                            launchSingleTop = true
                        }
                    },
                colorFilter = if (selectedItem == item) ColorFilter.tint(Color.Black)
                else ColorFilter.tint(Color.Gray)
            )
        }
    }
}