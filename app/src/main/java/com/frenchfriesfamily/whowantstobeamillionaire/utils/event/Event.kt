package com.frenchfriesfamily.whowantstobeamillionaire.utils.event

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return when (hasBeenHandled) {
            true -> null
            else -> {
                hasBeenHandled = true
                content
            }
        }
    }

}