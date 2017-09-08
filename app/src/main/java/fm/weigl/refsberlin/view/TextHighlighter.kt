package fm.weigl.refsberlin.view

import android.text.Spannable
import android.text.style.BackgroundColorSpan
import javax.inject.Inject

class TextHighlighter @Inject constructor() {

    var color = 0
    var toHighlight = ""

    fun highlightText(fullText: String): Spannable {

        val spanText = Spannable.Factory.getInstance().newSpannable(fullText)

        if (fullText.contains(toHighlight, ignoreCase = true)) {

            val startIndex = fullText.indexOf(toHighlight, ignoreCase = true)
            val endIndex = startIndex + toHighlight.length
            spanText.setSpan(BackgroundColorSpan(color), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        }


        return spanText
    }

}