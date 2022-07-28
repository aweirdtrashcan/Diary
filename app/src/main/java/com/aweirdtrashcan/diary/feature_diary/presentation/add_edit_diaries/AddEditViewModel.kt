package com.aweirdtrashcan.diary.feature_diary.presentation.add_edit_diaries

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aweirdtrashcan.diary.feature_diary.domain.model.Diary
import com.aweirdtrashcan.diary.feature_diary.domain.use_case.DiaryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val diaryUseCases: DiaryUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var state = mutableStateOf(AddEditState(color = Diary.colors.random().toArgb()))
        private set

    val sharedFlow = MutableSharedFlow<AddEditEvent>()

    private val currentDateHour: String =
        SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault()).format(Date())

    private var currentDiaryId: Int? = null

    init {
        savedStateHandle.get<Int>("diaryId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    diaryUseCases.getDiaryById(noteId)?.let { diary ->
                        currentDiaryId = diary.id
                        state.value = state.value.copy(
                            color = diary.color,
                            title = diary.title,
                            content = diary.content
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditEvent) {
        when(event) {
            is AddEditEvent.TitleChanged -> {
                state.value = state.value.copy(
                    title = event.value
                )
            }
            is AddEditEvent.ContentChanged -> {
                state.value = state.value.copy(
                    content = event.value
                )
            }
            is AddEditEvent.ColorChanged -> {
                state.value = state.value.copy(
                    color = event.color
                )
            }
            is AddEditEvent.Save -> {
                viewModelScope.launch {
                    sharedFlow.emit(AddEditEvent.LoadingSave)
                    try {
                        diaryUseCases.insertDiary(
                            Diary(
                                title = state.value.title,
                                content = state.value.content,
                                timeSaved = System.currentTimeMillis(),
                                formattedTimeSave = currentDateHour,
                                color = state.value.color,
                                id = currentDiaryId
                            )
                        )
                        sharedFlow.emit(AddEditEvent.DiarySaved)
                    } catch (e: IOException) {
                        sharedFlow.emit(AddEditEvent.DiarySaveError(e.message))
                    }
                }
            }
            else -> {}
        }
    }
}