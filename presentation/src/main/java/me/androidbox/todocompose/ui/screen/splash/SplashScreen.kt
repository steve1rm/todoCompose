package me.androidbox.todocompose.ui.screen.splash

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    var hasStartedAnimation by remember { mutableStateOf(false) }
    val offSetState by animateDpAsState(
        targetValue = if(hasStartedAnimation) 0.dp else 100.dp,
        animationSpec = tween(1000)) {
    }

    val alphaState by animateFloatAsState(
        targetValue = if(hasStartedAnimation) 1F else 0F,
        animationSpec = tween(1000)
    )

    LaunchedEffect(key1 = true, block = {
        hasStartedAnimation = true
        delay(SPLASH_SCREEN_DELAY)
        navigateToListScreen()
    })

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.splashScreenBackground)
        .offset(y = offSetState)
        .alpha(alpha = alphaState),
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