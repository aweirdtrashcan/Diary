package com.example.diary.feature_diary.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diary.ui.theme.blue
import com.example.diary.ui.theme.green
import com.example.diary.ui.theme.orange
import com.example.diary.ui.theme.pink

@Entity
data class Diary(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val content: String,
    val timeSaved: Long,
    val formattedTimeSave: String? = null,
    val color: Int
) {
    companion object {
        val colors: List<Color> = listOf(
            blue, orange, green, pink
        )
    }
}
