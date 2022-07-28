package com.aweirdtrashcan.diary.feature_diary.presentation.add_edit_diaries.components

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aweirdtrashcan.diary.feature_diary.domain.model.Diary
import com.aweirdtrashcan.diary.feature_diary.presentation.add_edit_diaries.AddEditEvent
import com.aweirdtrashcan.diary.feature_diary.presentation.add_edit_diaries.AddEditViewModel
import com.aweirdtrashcan.diary.feature_diary.presentation.util.UiBarsColors
import kotlinx.coroutines.launch

@Composable
fun AddEditScreen(
    navController: NavController,
    viewModel: AddEditViewModel = hiltViewModel(),
    diaryColor: Int
) {

    val state = viewModel.state.value
    val scaffold = rememberScaffoldState()
    val animatableColor = remember {
        Animatable(
            initialValue = Color(
                if (diaryColor != -1) diaryColor else state.color
            )
        )
    }
    val scope = rememberCoroutineScope()
    UiBarsColors(color = animatableColor.value)

    LaunchedEffect(key1 = true) {
        viewModel.sharedFlow.collect {
            when(it) {
                is AddEditEvent.DiarySaveError -> {
                    it.error?.let { errorMsg ->
                        scaffold.snackbarHostState.showSnackbar(
                            message = errorMsg
                        )
                    }
                }
                is AddEditEvent.DiarySaved -> {
                    scaffold.snackbarHostState.showSnackbar(
                        message = "Diário foi salvo com Sucesso"
                    )
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditEvent.Save(animatableColor.value.toArgb()))
                    navController.navigateUp()
                }) {
                Text(text = "Salvar")
            }
        },
        backgroundColor = MaterialTheme.colors.background,
        scaffoldState = scaffold
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(animatableColor.value),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Diary.colors.forEach { color ->
                        val colorInt = color.toArgb()
                        Box(
                            modifier = Modifier
                                .padding(14.dp)
                                .shadow(15.dp, CircleShape)
                                .size(50.dp)
                                .background(color)
                                .clip(CircleShape)
                                .border(
                                    width = 3.dp,
                                    color = if (state.color == colorInt) Color.Black
                                    else Color.Transparent,
                                    shape = CircleShape
                                )
                                .clickable {
                                    scope.launch {
                                        animatableColor.animateTo(color)
                                    }
                                    viewModel.onEvent(AddEditEvent.ColorChanged(colorInt))
                                }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
                TextField(
                    value = state.title,
                    onValueChange = {
                        viewModel.onEvent(AddEditEvent.TitleChanged(it))
                    },
                    label = {
                        Text(text = "Título")
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(14.dp))
                TextField(
                    value = state.content,
                    onValueChange = {
                        viewModel.onEvent(AddEditEvent.ContentChanged(it))
                    },
                    label = {
                        Text(text = "Conteúdo")
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

    }

}