package com.example.diary.feature_diary.presentation.diary

import com.example.diary.feature_diary.domain.model.Diary

sealed class DiaryEvent {
    data class SaveDiary(val diary: Diary): DiaryEvent()
    data class DeleteDiary(val diaryId: Int): DiaryEvent()
    data class InsertDiary(val diary: Diary): DiaryEvent()
}
