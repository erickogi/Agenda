package com.dev.agenda.Utils

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan

import java.util.Locale

object TextUtils {
    fun makeSectionOfTextBold(text: String, textToBold: String): SpannableStringBuilder {

        val builder = SpannableStringBuilder()

        if (textToBold.length > 0 && textToBold.trim { it <= ' ' } != "") {

            val testText = text.toLowerCase(Locale.US)
            val testTextToBold = textToBold.toLowerCase(Locale.US)
            val startingIndex = testText.indexOf(testTextToBold)
            val endingIndex = startingIndex + testTextToBold.length

            if (startingIndex < 0 || endingIndex < 0) {
                return builder.append(text)
            } else if (startingIndex >= 0 && endingIndex >= 0) {

                builder.append(text)
                builder.setSpan(StyleSpan(Typeface.BOLD), startingIndex, endingIndex, 0)
            }
        } else {
            return builder.append(text)
        }

        return builder
    }
}
