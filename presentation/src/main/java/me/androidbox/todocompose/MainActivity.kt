package me.androidbox.todocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import me.androidbox.todocompose.navigation.navigation
import me.androidbox.todocompose.ui.theme.ToDoComposeTheme
import me.androidbox.todocompose.viewmodel.ShareViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController
    private val shareViewModel: ShareViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoComposeTheme {
                navHostController = rememberNavController()
                navigation(
                    navHostController = navHostController,
                    shareViewModel = shareViewModel)
            }
        }
    }
}
