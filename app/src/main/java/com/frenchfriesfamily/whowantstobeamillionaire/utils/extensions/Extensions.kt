package com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions

import com.frenchfriesfamily.whowantstobeamillionaire.model.response.local.*
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.*

fun QuestionResult.convertToLocalResult(): LocalResult {
    return LocalResult(this.question)
}

fun QuestionResponse.convertToLocalResponse(): LocalResponse? {
    return this.results?.let { list ->
        LocalResponse(list.map { it.convertToLocalResult()})
    }
}

