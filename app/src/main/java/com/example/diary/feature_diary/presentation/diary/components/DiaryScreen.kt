package com.example.diary.feature_diary.presentation.diary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.diary.feature_diary.presentation.diary.DiaryEvent
import com.example.diary.feature_diary.presentation.diary.DiaryViewModel
import com.example.diary.feature_diary.presentation.util.Screen
import com.example.diary.feature_diary.presentation.util.UiBarsColors
import com.example.diary.ui.theme.blackSystemBar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun DiaryScreen(
    navController: NavController,
    viewModel: DiaryViewModel = hiltViewModel()
) {

    val state = viewModel.diariesList
    val scaffold = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    UiBarsColors(color = blackSystemBar)

    LaunchedEffect(key1 = true) {
        scope.launch {
            viewModel.sharedFlow.collect {
                when(it) {
                    is DiaryEvent.Error -> {
                        scaffold.snackbarHostState.showSnackbar(
                            message = it.message
                        )
                    }
                    is DiaryEvent.DiaryDeleted -> {
                        val restore =
                            scaffold.snackbarHostState.showSnackbar(
                            message = "Diário Apagado", actionLabel = "Desfazer"
                        )
                        if (restore == SnackbarResult.ActionPerformed) {
                            viewModel.onEvent(DiaryEvent.RestoreDiary)
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddEditScreen.route)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Diary")
            }
        },
        scaffoldState = scaffold
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                items(state.value) { diary ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(Color(diary.color))
                            .clickable {
                                navController.navigate(
                                    Screen.AddEditScreen.route +
                                            "?diaryId=${diary.id}&diaryColor=${diary.color}"
                                )
                            }
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .size(50.dp)
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = diary.title,
                                style = MaterialTheme.typography.h6
                            )
                            IconButton(onClick = {
                                viewModel.onEvent(DiaryEvent.DeleteDiary(diary))
                            }) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Diary")
                            }
                        }
                    }
                }
            }
        }
    }
}