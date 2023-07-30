@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.instagramclone.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.instagramclone.presentation.auth.AuthenticationViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    authenticationViewModel: AuthenticationViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "HOME")
        }
        BottomNavigationBar(
            navHostController = navHostController,
            selectedItem = BottomNavItems.HOME
        )
    }
}