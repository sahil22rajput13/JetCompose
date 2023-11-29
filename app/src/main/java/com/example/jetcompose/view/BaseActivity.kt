package com.example.jetcompose.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetcompose.utils.SharedPreferenceModule
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val sharedPref = SharedPreferenceModule.provideSharedPreference(LocalContext.current)
            NavHost(navController = navController, startDestination = "onboarding") {
                composable("onboarding") {
                    CheckOnBoardScreen(navController)
                }
                composable("signIn") {
                    CheckSigInComposeScreen()
                }
            }
        }
    }
}

