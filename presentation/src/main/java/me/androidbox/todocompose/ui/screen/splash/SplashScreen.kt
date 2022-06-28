package me.androidbox.todocompose.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import me.androidbox.todocompose.Constant.SPLASH_SCREEN_DELAY
import me.androidbox.todocompose.R
import me.androidbox.todocompose.ui.theme.LOGO_HEIGHT
import me.androidbox.todocompose.ui.theme.ToDoComposeTheme
import me.androidbox.todocompose.ui.theme.splashScreenBackground

@Composable
fun SplashScreen(
    navigateToListScreen: () -> Unit
) {
    LaunchedEffect(key1 = true, block = {
        delay(SPLASH_SCREEN_DELAY)
        navigateToListScreen()
    })

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.splashScreenBackground),
        contentAlignment = Alignment.Center) {

        Image(
            modifier = Modifier.size(LOGO_HEIGHT),
            painter = painterResource(R.drawable.ic_logo_dark),
            contentDescription = stringResource(R.string.splash_screen)
        )
    }
}

@Composable
fun getLogo(): Int  {
    return if(isSystemInDarkTheme()) {
        R.drawable.ic_logo_dark
    }
    else {
        R.drawable.ic_logo_light
    }
}

@Composable
@Preview
fun SplashScreenPreviewLight() {
    SplashScreen(navigateToListScreen = {})
}

@Composable
@Preview
fun SplashScreenPreviewDark() {
    ToDoComposeTheme(darkTheme = true) {
        SplashScreen(navigateToListScreen = {})
    }
}