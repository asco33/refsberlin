package fm.weigl.refsberlin.android

import android.content.Context
import android.support.v4.content.ContextCompat
import javax.inject.Inject

class ContextCompatWrapper @Inject constructor(private val context: Context) {
    fun getColor(colorId: Int): Int
            = ContextCompat.getColor(context, colorId)
}