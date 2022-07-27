package com.example.diary.feature_diary.di

import android.app.Application
import androidx.room.Room
import com.example.diary.feature_diary.data.local.DiaryDao
import com.example.diary.feature_diary.data.local.DiaryDb
import com.example.diary.feature_diary.data.repository.DiaryRepositoryImpl
import com.example.diary.feature_diary.domain.repository.DiaryRepository
import com.example.diary.feature_diary.domain.use_case.DiaryUseCases
import com.example.diary.feature_diary.domain.use_case.GetDiaries
import com.example.diary.feature_diary.domain.use_case.GetDiaryById
import com.example.diary.feature_diary.domain.use_case.InsertDiary
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDiaryDao(
        application: Application
    ) : DiaryDao {
        return Room.databaseBuilder(
            application,
            DiaryDb::class.java,
            "diaryDb"
        ).build().dao
    }

    @Provides
    @Singleton
    fun provideDiaryRepository(dao: DiaryDao): DiaryRepository {
        return DiaryRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideDiaryUseCases(repository: DiaryRepository): DiaryUseCases {
        return DiaryUseCases(
            getDiaries = GetDiaries(repository),
            getDiaryById = GetDiaryById(repository),
            insertDiary = InsertDiary(repository)
        )
    }

}