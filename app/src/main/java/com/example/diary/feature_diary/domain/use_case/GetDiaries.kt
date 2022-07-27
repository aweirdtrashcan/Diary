package com.example.diary.feature_diary.domain.use_case

import com.example.diary.feature_diary.domain.model.Diary
import com.example.diary.feature_diary.domain.repository.DiaryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDiaries (
    private val repository: DiaryRepository
) {

    operator fun invoke(): Flow<List<Diary>> {
        return repository.getDiaries()
    }

}