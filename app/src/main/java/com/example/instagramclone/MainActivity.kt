package com.example.instagramclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instagramclone.presentation.SplashScreen
import com.example.instagramclone.presentation.auth.AuthenticationViewModel
import com.example.instagramclone.presentation.auth.LoginScreen
import com.example.instagramclone.presentation.auth.SignUpScreen
import com.example.instagramclone.presentation.home.HomeScreen
import com.example.instagramclone.presentation.profile.ProfileScreen
import com.example.instagramclone.presentation.search.SearchScreen
import com.example.instagramclone.ui.theme.InstagramCloneTheme
import com.example.instagramclone.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramCloneTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val authenticationViewModel: AuthenticationViewModel = hiltViewModel()
                    InstagramCloneApp(navController, authenticationViewModel)
                }
            }
        }
    }
}

@Composable
fun InstagramCloneApp(
    navController: NavHostController,
    authenticationViewModel: AuthenticationViewModel
) {
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController, authViewModel = authenticationViewModel)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(
                navController = navController,
                authenticationViewModel = authenticationViewModel
            )
        }
        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen(
                navController = navController,
                authenticationViewModel = authenticationViewModel
            )
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                navHostController = navController,
                authenticationViewModel = authenticationViewModel
            )
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController)
        }
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController)
        }
    }
}