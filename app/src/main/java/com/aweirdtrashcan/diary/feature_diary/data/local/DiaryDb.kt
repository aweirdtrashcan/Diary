package com.aweirdtrashcan.diary.feature_diary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aweirdtrashcan.diary.feature_diary.domain.model.Diary

@Database(
    entities = [Diary::class],
    version = 1
)
abstract class DiaryDb: RoomDatabase() {
    abstract val dao: DiaryDao
}
