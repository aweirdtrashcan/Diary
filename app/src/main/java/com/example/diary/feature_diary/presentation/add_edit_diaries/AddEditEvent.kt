package com.example.diary.feature_diary.presentation.add_edit_diaries

sealed class AddEditEvent {
    data class ColorChanged(val color: Int): AddEditEvent()
    data class TitleChanged(val value: String): AddEditEvent()
    data class ContentChanged(val value: String): AddEditEvent()
    object Save: AddEditEvent()
    object LoadingSave: AddEditEvent()
    object DiarySaved: AddEditEvent()
    data class DiarySaveError(val error: String?): AddEditEvent()
}
