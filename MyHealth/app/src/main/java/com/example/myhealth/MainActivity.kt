package com.example.myhealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.myhealth.navigation.Destinations
import com.example.myhealth.navigation.Destinations.*
import com.example.myhealth.navigation.NavigationHost
import com.example.myhealth.presentation.Login
import com.example.myhealth.presentation.components.BottomNavigationBar
import com.example.myhealth.presentation.screens.Registration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val navigationItems = listOf(
        IMC,
        PGC,
        Diabetes,
        Perfil
    )


    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController, items = navigationItems) }
    ){
        NavigationHost(navController)
    }
}
