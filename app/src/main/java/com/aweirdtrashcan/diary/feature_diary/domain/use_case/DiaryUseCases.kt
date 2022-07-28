package com.aweirdtrashcan.diary.feature_diary.domain.use_case

data class DiaryUseCases(
    val getDiaries: GetDiaries,
    val getDiaryById: GetDiaryById,
    val insertDiary: InsertDiary,
    val deleteDiary: DeleteDiary
)
