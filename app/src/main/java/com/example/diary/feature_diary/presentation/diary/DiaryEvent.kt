package com.example.diary.feature_diary.presentation.diary

sealed class DiaryEvent {
    object GetAllDiaries: DiaryEvent()
    object AddEditDiary: DiaryEvent()
    object Loading: DiaryEvent()
    object Loaded: DiaryEvent()
    data class Error(val message: String): DiaryEvent()
    data class DeleteDiary(val diaryId: Int): DiaryEvent()
}
