package com.example.diary.feature_diary.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.diary.feature_diary.presentation.add_edit_diaries.components.AddEditScreen
import com.example.diary.feature_diary.presentation.diary.components.DiaryScreen
import com.example.diary.feature_diary.presentation.util.Screen
import com.example.diary.ui.theme.DiaryTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiaryTheme(darkTheme = false) {

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.DiaryScreen.route
                ) {
                    composable(route = Screen.DiaryScreen.route) {
                        DiaryScreen(navController = navController)
                    }
                    composable(
                        route = Screen.AddEditScreen.route +
                                "?diaryId={diaryId}&diaryColor={diaryColor}",
                        arguments = listOf(
                            navArgument(
                                name = "diaryId"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                            navArgument(
                                name = "diaryColor"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ) {
                        val color: Int = it.arguments?.getInt("diaryColor") ?: -1
                        AddEditScreen(
                            navController = navController,
                            diaryColor = color
                        )
                    }
                }
            }
        }
    }
}