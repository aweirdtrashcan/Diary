package com.example.diary.feature_diary.presentation.util

sealed class Screen(val route: String) {
    object DiaryScreen: Screen("diary_screen")
    object AddEditScreen: Screen("add_edit_screen")
}
