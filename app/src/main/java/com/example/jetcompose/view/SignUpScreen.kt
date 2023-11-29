package com.example.jetcompose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetcompose.R
import com.example.jetcompose.utils.SharedPreference
import com.example.jetcompose.utils.SharedPreferenceModule
import com.example.jetcompose.utils.Toast

@Composable
fun CheckSigUpComposeScreen() {
    SharedPreferenceModule.provideSharedPreference(LocalContext.current)
        .putBoolean(SharedPreference.Key.ISLANDINGCOMPLETE, true)
    if (!SharedPreferenceModule.provideSharedPreference(LocalContext.current)
            .getBoolean(SharedPreference.Key.ISUSERSIGNUP)
    ) {
        SignUpComposeScreen()
    } else {
        LocalContext.current.Toast(message = "Choose Role")
    }
}

@Composable
fun SignUpComposeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                painter = painterResource(id = R.drawable.standard_collection_1), // Change the image resource ID
                contentDescription = "image description",
                contentScale = ContentScale.None
            )

            Text(
                text = "Sign Up", // Change the text to "Sign Up"
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    fontFamily = FontFamily(Font(R.font.karlaextrabold)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF040C1B),
                )
            )

            Text(
                text = "Create a new account to access our services", // Modify the description
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    fontFamily = FontFamily(Font(R.font.karlalight)),
                    fontWeight = FontWeight(300),
                    color = Color(0xFF3E444F),
                )
            )

            Text(
                text = "Email Address",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    fontFamily = FontFamily(Font(R.font.karlaregular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF040C1B),
                )
            )
            // Add more fields for signup as needed
        }
    }
}
