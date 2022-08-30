package com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions

import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuation.AND_TEXT
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuation.APOSTROPHE_SYMBOL
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuation.APOSTROPHE_TEXT
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuation.E_ACUTE_SYMBOL
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuation.E_ACUTE_TEXT
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuation.QUOTATION_SYMBOL
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuation.QUOTATION_TEXT

fun String.replacePunctuationTextsWithSymbols() =
    this.replace(QUOTATION_TEXT, QUOTATION_SYMBOL)
        .replace(APOSTROPHE_TEXT, APOSTROPHE_SYMBOL)
        .replace(AND_TEXT, APOSTROPHE_SYMBOL)
        .replace(E_ACUTE_TEXT, E_ACUTE_SYMBOL)