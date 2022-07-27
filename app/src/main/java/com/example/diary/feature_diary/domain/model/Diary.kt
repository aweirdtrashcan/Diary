package com.example.diary.feature_diary.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Diary(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val timeSaved: String,
    val timeEdited: String? = null,
    val color: Int
)
