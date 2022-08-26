package com.frenchfriesfamily.whowantstobeamillionaire.model.network

sealed class State<out T> {
    object Loading : State<Nothing>()
    data class Success<T>(val data: T?) : State<T>()
    data class Error(val message: String) : State<Nothing>()

    fun toData(): T? = if(this is Success) data else null
}
