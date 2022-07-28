package com.example.diary.feature_diary.domain.use_case

import com.example.diary.feature_diary.domain.model.Diary
import com.example.diary.feature_diary.domain.repository.DiaryRepository
import java.io.IOException

class InsertDiary(
    private val repository: DiaryRepository
) {

    suspend operator fun invoke(diary: Diary) {
        return repository.insertDiary(diary)
    }

}