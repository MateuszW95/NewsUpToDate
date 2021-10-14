package com.mw.news.base

/**
 * We will use this sealed class for network calls and connection between ViewModel and UI
 */
sealed class State<out R> {
    data class Success<out T>(val data: T) : State<T>()
    data class Error(val exception: Throwable) : State<Nothing>()
    object Loading : State<Nothing>()
}