package com.example.diary.feature_diary.presentation.diary

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diary.feature_diary.domain.model.Diary
import com.example.diary.feature_diary.domain.use_case.DiaryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val diaryUseCases: DiaryUseCases
): ViewModel() {

    var state = mutableStateOf(emptyList<Diary>())
        private set

    var sharedFlow = MutableSharedFlow<DiaryEvent>()
        private set

    fun onEvent(event: DiaryEvent) {
        when(event) {
            is DiaryEvent.InsertDiary -> {
                viewModelScope.launch {
                    diaryUseCases.insertDiary(event.diary)
                }
            }
            is DiaryEvent.SaveDiary -> {
                viewModelScope.launch {
//                    diaryUseCases.getDiaryById(event.diary.id)
                }
            }
        }
    }

}