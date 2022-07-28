package com.aweirdtrashcan.diary.feature_diary.presentation.diary

import com.aweirdtrashcan.diary.feature_diary.domain.model.Diary

sealed class DiaryEvent {
    data class Error(val message: String): DiaryEvent()
    data class DeleteDiary(val diary: Diary): DiaryEvent()
    object RestoreDiary: DiaryEvent()
    object DiaryDeleted: DiaryEvent()
}
