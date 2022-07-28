package com.aweirdtrashcan.diary.feature_diary.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun UiBarsColors(
    color: Color
) {
    val sysUi = rememberSystemUiController()
    sysUi.setSystemBarsColor(color)
}