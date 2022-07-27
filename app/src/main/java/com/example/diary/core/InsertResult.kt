package com.example.diary.core

sealed class InsertResult(val isSuccessful: Boolean, val message: String? = null) {
    class Success(isSuccessful: Boolean, message: String? = null): InsertResult(isSuccessful, message)
    class Error(isSuccessful: Boolean, message: String): InsertResult(isSuccessful, message)
}