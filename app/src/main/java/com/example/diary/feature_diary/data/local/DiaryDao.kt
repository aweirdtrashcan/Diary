package com.example.diary.feature_diary.data.local

import androidx.room.*
import com.example.diary.feature_diary.domain.model.Diary
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {

    @Query("SELECT * FROM diary")
    fun getDiaries(): Flow<List<Diary>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiary(diary: Diary)

    @Delete
    suspend fun deleteDiary(diary: Diary)

    @Query("SELECT * FROM diary WHERE id LIKE :id")
    suspend fun getDiaryById(id: Int): Diary?

}