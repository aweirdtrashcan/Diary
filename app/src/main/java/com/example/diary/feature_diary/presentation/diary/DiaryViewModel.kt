package com.example.diary.feature_diary.presentation.diary

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diary.feature_diary.domain.model.Diary
import com.example.diary.feature_diary.domain.use_case.DiaryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val diaryUseCases: DiaryUseCases
): ViewModel() {

    var diariesList = mutableStateOf(emptyList<Diary>())
        private set

    var sharedFlow = MutableSharedFlow<DiaryEvent>()
        private set

    private var tempDiary : Diary? = null

    init {
        getAllDiaries()
    }

    fun onEvent(event: DiaryEvent) {
        when(event) {
            is DiaryEvent.DeleteDiary -> {
                viewModelScope.launch {
                    tempDiary = event.diary
                    diaryUseCases.deleteDiary(event.diary)
                    sharedFlow.emit(DiaryEvent.DiaryDeleted)
                }
            }
            is DiaryEvent.RestoreDiary -> {
                viewModelScope.launch {
                    diaryUseCases.insertDiary(tempDiary ?: return@launch)
                    tempDiary = null
                }
            }
        }
    }

    private fun getAllDiaries() {
        viewModelScope.launch {
            try {
                diaryUseCases.getDiaries().collect {
                    diariesList.value = it
                }
            } catch (e: IOException) {
                sharedFlow.emit(DiaryEvent.Error(e.message.toString()))
            }
        }
    }

}