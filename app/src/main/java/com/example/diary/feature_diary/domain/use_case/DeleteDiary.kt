package com.example.diary.feature_diary.domain.use_case

import com.example.diary.feature_diary.domain.model.Diary
import com.example.diary.feature_diary.domain.repository.DiaryRepository
import javax.inject.Inject

class DeleteDiary @Inject constructor(
    private val repository: DiaryRepository
) {
    suspend operator fun invoke(diary: Diary) = repository.deleteDiary(diary)
}