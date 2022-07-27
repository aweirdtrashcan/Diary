package com.example.diary.feature_diary.domain.repository

import com.example.diary.feature_diary.domain.model.Diary
import kotlinx.coroutines.flow.Flow

interface DiaryRepository {

    fun getDiaries(): Flow<List<Diary>>

    suspend fun insertDiary(diary: Diary)

    suspend fun deleteDiary(diary: Diary)

    suspend fun getDiaryById(id: Int): Diary?

}