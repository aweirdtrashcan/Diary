package com.example.diary.feature_diary.data.repository

import com.example.diary.feature_diary.data.local.DiaryDao
import com.example.diary.feature_diary.domain.model.Diary
import com.example.diary.feature_diary.domain.repository.DiaryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    private val dao: DiaryDao
): DiaryRepository {

    override fun getDiaries(): Flow<List<Diary>> {
        return dao.getDiaries()
    }

    override suspend fun insertDiary(diary: Diary) {
        return dao.insertDiary(diary)
    }

    override suspend fun deleteDiary(diary: Diary) {
        return dao.deleteDiary(diary)
    }

    override suspend fun getDiaryById(id: Int): Diary? {
        return dao.getDiaryById(id)
    }
}