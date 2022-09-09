package com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions

import org.jsoup.Jsoup

fun String.replacePunctuationTextsWithSymbols(): String = Jsoup.parse(this).text()