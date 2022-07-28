package com.aweirdtrashcan.diary.feature_diary.presentation.add_edit_diaries

data class AddEditState(
    val color: Int,
    val title: String = "",
    val content: String = ""
)