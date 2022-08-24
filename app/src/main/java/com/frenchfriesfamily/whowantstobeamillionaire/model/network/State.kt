package com.frenchfriesfamily.whowantstobeamillionaire.model.network

sealed class State<out T> {
    object Loading : State<Nothing>()
    data class Success<T>(val data: T) : State<T>()
    data class Fail(val message: String) : State<Nothing>()
}
