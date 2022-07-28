package com.aweirdtrashcan.diary.feature_diary.presentation.add_edit_diaries

sealed class AddEditEvent {
    data class ColorChanged(val color: Int): AddEditEvent()
    data class TitleChanged(val value: String): AddEditEvent()
    data class ContentChanged(val value: String): AddEditEvent()
    data class Save(val color: Int): AddEditEvent()
    object LoadingSave: AddEditEvent()
    object DiarySaved: AddEditEvent()
    data class DiarySaveError(val error: String?): AddEditEvent()
}
