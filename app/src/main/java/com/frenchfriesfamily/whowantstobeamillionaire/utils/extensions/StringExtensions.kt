package com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions

import com.frenchfriesfamily.whowantstobeamillionaire.model.response.Question
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.Answer
import com.frenchfriesfamily.whowantstobeamillionaire.view.game.enums.AnswerState
import com.github.mikephil.charting.data.BarEntry
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.jsoup.Jsoup
import java.util.concurrent.TimeUnit
import kotlin.random.Random

fun String.replacePunctuationTextsWithSymbols(): String = Jsoup.parse(this).text()

fun Question.toAnswer(): List<Answer>? {
    val list = this.incorrectAnswers?.toMutableList()?.apply {
        this.add(correctAnswer)
    }?.map { Answer(it, AnswerState.IS_DEFAULT) }?.shuffled()
    return list
}

fun String.getProbableAnswer(answers: List<Answer?>?): Char {
    val probabilities = mutableListOf<Float>()

    answers?.forEach { answer ->
        when (answer?.answer) {
            this -> probabilities.add(Random.nextFloat() + 1f)
            else -> probabilities.add(Random.nextFloat())
        }
    }

    val maximumValue = probabilities.max()

    return (probabilities.indexOf(maximumValue) + 65).toChar()
}

fun List<Answer?>?.getProbableAudienceAnswers(correctAnswer: String?): ArrayList<BarEntry> {
    val barEntries = arrayListOf<BarEntry>()
    val probabilities = mutableListOf<Float>()

    this?.forEach { answer ->
        when (answer?.answer) {
            correctAnswer -> probabilities.add(Random.nextFloat() + 1f)
            else -> probabilities.add(Random.nextFloat())
        }
    }

    val sum = probabilities.sum()
    probabilities.forEachIndexed { index, probability ->
        barEntries.add(BarEntry(probability / sum * 100, index))
    }

    return barEntries
}
