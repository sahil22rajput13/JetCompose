@file:OptIn(
    ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalFoundationApi::class
)

package com.example.jetcompose.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jetcompose.R
import com.example.jetcompose.models.onBoard.OnBoardSample
import com.example.jetcompose.utils.SharedPreference
import com.example.jetcompose.utils.SharedPreferenceModule
import com.example.jetcompose.utils.Toast

private val onBoardList: ArrayList<OnBoardSample> = arrayListOf(
    OnBoardSample(
        R.drawable.group_41,
        "We help you find your dream job more easily",
        "Lorem ipsum dolor sit amet consectetur. Amet sit at fermentum id vitae lacus id natoque ante. Placerat urna risus quisque urna suspendisse tellus."
    ),
    OnBoardSample(
        R.drawable.group_22,
        "Keep track of your nearby jobs",
        "Lorem ipsum dolor sit amet consectetur. Amet sit at fermentum id vitae lacus id natoque ante. Placerat urna risus quisque urna suspendisse tellus."
    ),
    OnBoardSample(
        R.drawable.illustration,
        "Finding the right job made easy with Job Tracker",
        "Lorem ipsum dolor sit amet consectetur. Amet sit at fermentum id vitae lacus id natoque ante. Placerat urna risus quisque urna suspendisse tellus."
    )
)

@Composable
fun CheckOnBoardScreen(navController: NavHostController) {
    val sharedPref = SharedPreferenceModule.provideSharedPreference(LocalContext.current)
    if (!sharedPref.getBoolean(SharedPreference.Key.ISLANDINGCOMPLETE)) {
        OnBoardPager(navController)
    } else if (sharedPref.getBoolean(SharedPreference.Key.ISUSERSIGNIN)) {
        LocalContext.current.Toast(message = "Home Screen is already")
    } else if (!sharedPref.getBoolean(SharedPreference.Key.ISUSERSIGNUP)) {
        CheckSigUpComposeScreen()
    }
}

@Composable
fun OnBoardPager(navController: NavHostController) {
    val pagerState = rememberPagerState { onBoardList.size }
    val alphaValue = if (pagerState.currentPage == 2) 0f else 1f

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            OnBoardPage(it, navController)
        }

        // Pager indicators (dots)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(alphaValue)
                .padding(end = 20.dp, start = 20.dp, bottom = 30.dp)
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.Start),
                verticalAlignment = Alignment.Top
            ) {
                OnBoardPagerIndicators(pagerState)
            }
            val isVisible = alphaValue == 1f
            OnBoardSkipButton(isVisible, navController)
        }
    }
}

@Composable
fun OnBoardPagerIndicators(pagerState: PagerState) {
    repeat(pagerState.pageCount) { iteration ->
        val color =
            if (pagerState.currentPage == iteration) Color.Black else Color.LightGray
        Box(
            Modifier
                .width(30.dp)
                .height(5.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color)
                .size(16.dp)
        )
    }
}

@Composable
fun OnBoardPage(position: Int, navController: NavHostController) {
    val onBoardItem = onBoardList[position]

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
    ) {
        OnBoardImage(onBoardItem.image)
        Spacer(modifier = Modifier.height(32.dp))
        OnBoardTitle(onBoardItem.title)
        Spacer(modifier = Modifier.height(16.dp))
        OnBoardDescription(onBoardItem.description)
        Spacer(modifier = Modifier.height(33.dp))
        OnBoardProfileInfo(position == 0)
        OnBoardProfileInfo2(position == 1)
        SignInButton(position == 2, navController)
    }
}

@Composable
fun OnBoardImage(image: Int) {
    Image(
        painter = painterResource(id = image),
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp, 100.dp, 50.dp),
        contentDescription = "Onboarding Image"
    )
}

@Composable
fun SignInButton(isVisible: Boolean, navController: NavHostController) {
    val alphaValue = if (isVisible) 1f else 0f

    Image(
        painter = painterResource(id = R.drawable.btn_signup),
        modifier = Modifier
            .fillMaxWidth()
            .alpha(alphaValue)
            .clickable(enabled = isVisible) {
                navController.navigate("signIn") {
                    popUpTo("onboarding") {
                        inclusive = true
                    }
                }
            },
        contentDescription = null
    )
}

@Composable
fun OnBoardSkipButton(isVisible: Boolean, navController: NavHostController) {
    Text(
        text = "Skip",
        modifier = Modifier
            .clickable(enabled = isVisible) {
                navController.navigate("signIn") {
                    popUpTo("onboarding") {
                        inclusive = true
                    }
                }
            },
        style = TextStyle(
            fontSize = 14.sp,
            lineHeight = 22.sp,
            fontFamily = FontFamily(Font(R.font.karlaregular)),
            fontWeight = FontWeight(300),
            color = Color(0xFF3E444F),
            textDecoration = TextDecoration.Underline,
        )
    )
}

@Composable
fun OnBoardProfileInfo(isVisible: Boolean) {
    val alphaValue = if (isVisible) 1f else 0f

    Column(
        modifier = Modifier
            .padding(start = 45.dp)
            .alpha(alphaValue),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        ProfileInfoRow("Job recommendations based on your profile")
        ProfileInfoRow("Profile highlights, direct jobs and more")
    }
}

@Composable
fun OnBoardProfileInfo2(isVisible: Boolean) {
    val alphaValue = if (isVisible) 1f else 0f

    Row(
        modifier = Modifier.alpha(alphaValue),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ProfileInfoRow("Remote")
        ProfileInfoRow("Part-Time")
        ProfileInfoRow("Full-Time")
    }
}

@Composable
fun ProfileInfoRow(text: String) {
    Row(
        modifier = Modifier
            .wrapContentWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.frame),
            contentDescription = "Profile Image",
            contentScale = ContentScale.None
        )
        Text(
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 22.sp,
                fontFamily = FontFamily(Font(R.font.karlaregular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF3E444F),
            )
        )
    }
}

@Composable
fun OnBoardTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 24.sp,
            lineHeight = 32.sp,
            fontFamily = FontFamily(Font(R.font.karlaextrabold)),
            fontWeight = FontWeight(700),
            textAlign = TextAlign.Center,
        )
    )
}

@Composable
fun OnBoardDescription(description: String) {
    Text(
        text = description,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 14.sp,
            lineHeight = 22.sp,
            fontFamily = FontFamily(Font(R.font.karlalight)),
            fontWeight = FontWeight(300),
            color = Color(0xFF3E444F),
            textAlign = TextAlign.Center,
        )
    )
}
