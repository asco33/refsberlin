package fm.weigl.refsberlin.android

import android.content.Context
import android.support.v4.content.ContextCompat
import javax.inject.Inject

/**
 * Created by asco on 4/20/17.
 */
class ContextCompatWrapper @Inject constructor(private val context: Context) {
    fun getColor(colorId: Int): Int
            = ContextCompat.getColor(context, colorId)
}