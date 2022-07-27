package com.example.diary.feature_diary.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun ColorPicker(
    modifier: Modifier = Modifier,
    colors: List<Int>,
    selectedColor: Int,
    onColorSelected: (Int) -> Unit
) {

    Row(
        modifier = modifier
    ) {
        colors.forEach { color ->
            Box(
                modifier = Modifier
                    .padding(14.dp)
                    .background(Color(color))
                    .size(50.dp)
                    .clip(RoundedCornerShape(percent = 50))
                    .clickable { onColorSelected(color) }
                    .border(
                        width = 3.dp,
                        color = if(selectedColor == color) Color.Black
                        else Color.Transparent,
                        shape = CircleShape
                    )
            )
        }
    }

}