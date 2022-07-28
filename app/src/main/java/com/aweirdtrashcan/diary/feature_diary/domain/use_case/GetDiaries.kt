package com.aweirdtrashcan.diary.feature_diary.domain.use_case

import com.aweirdtrashcan.diary.feature_diary.domain.model.Diary
import com.aweirdtrashcan.diary.feature_diary.domain.repository.DiaryRepository
import kotlinx.coroutines.flow.Flow

class GetDiaries (
    private val repository: DiaryRepository
) {

    operator fun invoke(): Flow<List<Diary>> {
        return repository.getDiaries()
    }

}