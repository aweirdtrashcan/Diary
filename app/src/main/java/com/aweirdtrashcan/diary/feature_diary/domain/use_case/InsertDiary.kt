package com.aweirdtrashcan.diary.feature_diary.domain.use_case

import com.aweirdtrashcan.diary.feature_diary.domain.model.Diary
import com.aweirdtrashcan.diary.feature_diary.domain.repository.DiaryRepository

class InsertDiary(
    private val repository: DiaryRepository
) {

    suspend operator fun invoke(diary: Diary) {
        return repository.insertDiary(diary)
    }

}