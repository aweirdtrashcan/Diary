package com.example.diary.feature_diary.domain.use_case

import com.example.diary.core.InsertResult
import com.example.diary.feature_diary.domain.model.Diary
import com.example.diary.feature_diary.domain.repository.DiaryRepository
import java.io.IOException

class InsertDiary(
    private val repository: DiaryRepository
) {

    suspend operator fun invoke(diary: Diary): InsertResult {
        return try {
            repository.insertDiary(diary)
            InsertResult.Success(true)
        } catch (e: IOException) {
            InsertResult.Error(false, e.message ?: "Error: IOException")
        }
    }

}