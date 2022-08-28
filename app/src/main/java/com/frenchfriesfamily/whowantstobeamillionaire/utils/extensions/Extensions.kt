package com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions

import com.frenchfriesfamily.whowantstobeamillionaire.model.response.QuestionResponse
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.QuestionResult
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.local.LocalResponse
import com.frenchfriesfamily.whowantstobeamillionaire.model.response.local.LocalResult

fun QuestionResult.convertToLocalResult(): LocalResult {
    return LocalResult(this.question)
}

fun QuestionResponse.convertToLocalResponse(): LocalResponse? {
    return this.results?.let { list ->
        LocalResponse(list.map { it.convertToLocalResult() })
    }
}

