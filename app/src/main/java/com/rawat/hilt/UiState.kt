package com.rawat.hilt

sealed class UiState<out T> {
    data class Success<T : Any>(val data: T?) : UiState<T>()
    data class Error<T>(val message: T) : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
}