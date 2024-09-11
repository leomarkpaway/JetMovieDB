package com.leomarkpaway.movieapp.presentation.home.state

sealed class DataState<T> {
    data class Loading<T>(val isLoading: Boolean) : DataState<T>()
    data class Success<T>(val data: T) : DataState<T>()
    data class Error<T>(val uiComponent: UIComponent) : DataState<T>()
}

sealed class UIComponent {
    data class Toast(val text: String) : UIComponent()
    data class SnackBar(val text: String) : UIComponent()
    data class Dialog(val title: String, val message: String) : UIComponent()
}