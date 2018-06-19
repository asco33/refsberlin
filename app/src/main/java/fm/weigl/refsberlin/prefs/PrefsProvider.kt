package fm.weigl.refsberlin.prefs

import android.content.Context

object PrefsProvider {

    val key = "app_prefs"

    fun prefs(context: Context) = context.getSharedPreferences(key, Context.MODE_PRIVATE)

}