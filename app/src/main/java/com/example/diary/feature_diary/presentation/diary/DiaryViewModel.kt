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

    fun onEvent(event: DiaryEvent) {
        when(event) {
            DiaryEvent.GetAllDiaries -> {
                getAllDiaries()
            }
        }
    }

    private fun getAllDiaries() {
        viewModelScope.launch {
            sharedFlow.emit(DiaryEvent.Loading)
            try {
                diaryUseCases.getDiaries().collect {
                    diariesList.value = it
                    sharedFlow.emit(DiaryEvent.Loaded)
                }
            } catch (e: IOException) {
                sharedFlow.emit(DiaryEvent.Error(e.message.toString()))
            }
        }
    }

}