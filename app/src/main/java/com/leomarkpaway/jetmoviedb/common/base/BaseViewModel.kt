package com.leomarkpaway.jetmoviedb.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    // LiveData to store any errors that occur during a data operation
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    // Function to handle errors protected
    private fun handleError(throwable: Throwable) {

        // Log the error (optional)
        throwable.printStackTrace()

        // Set the error message to the errorLiveData
        _errorLiveData.postValue(throwable.message)
    }

    // Function to launch a coroutine that collects a Flow and handles errors
    protected fun <T> launchDataOperation(
        flow: Flow<T>,
        onSuccess: (T) -> Unit
    ) {
        viewModelScope.launch {
            flow.catch { throwable ->
                handleError(throwable)
            }.collect { data ->
                onSuccess(data)
            }
        }
    }
}