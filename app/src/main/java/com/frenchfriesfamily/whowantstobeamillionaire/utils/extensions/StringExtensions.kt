package com.frenchfriesfamily.whowantstobeamillionaire.utils.extensions

import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuations.AND_SYMBOL
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuations.AND_TEXT
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuations.APOSTROPHE_SYMBOL
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuations.APOSTROPHE_TEXT
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuations.E_ACUTE_SYMBOL
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuations.E_ACUTE_TEXT
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuations.LEFT_DOUBLE_MARK_SYMBOL
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuations.LEFT_DOUBLE_MARK_TEXT
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuations.QUOTATION_SYMBOL
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuations.QUOTATION_TEXT
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuations.RIGHT_DOUBLE_MARK_SYMBOL
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuations.RIGHT_DOUBLE_MARK_TEXT
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuations.U_UMLAUT_SYMBOL
import com.frenchfriesfamily.whowantstobeamillionaire.utils.Constants.Punctuations.U_UMLAUT_TEXT

fun String.replacePunctuationTextsWithSymbols() =
    this.replace(QUOTATION_TEXT, QUOTATION_SYMBOL)
        .replace(APOSTROPHE_TEXT, APOSTROPHE_SYMBOL)
        .replace(AND_TEXT, AND_SYMBOL)
        .replace(E_ACUTE_TEXT, E_ACUTE_SYMBOL)
        .replace(U_UMLAUT_TEXT, U_UMLAUT_SYMBOL)
        .replace(LEFT_DOUBLE_MARK_TEXT, LEFT_DOUBLE_MARK_SYMBOL)
        .replace(RIGHT_DOUBLE_MARK_TEXT, RIGHT_DOUBLE_MARK_SYMBOL)