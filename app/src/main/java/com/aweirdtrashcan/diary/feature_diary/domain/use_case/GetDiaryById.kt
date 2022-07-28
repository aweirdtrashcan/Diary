package com.aweirdtrashcan.diary.feature_diary.domain.use_case

import com.aweirdtrashcan.diary.feature_diary.domain.model.Diary
import com.aweirdtrashcan.diary.feature_diary.domain.repository.DiaryRepository

class GetDiaryById(
    private val repository: DiaryRepository
) {

    suspend operator fun invoke(id: Int): Diary? {
        return repository.getDiaryById(id)
    }

}