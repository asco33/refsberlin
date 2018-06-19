package fm.weigl.refsberlin.cache

import android.content.SharedPreferences
import com.google.gson.Gson

abstract class SimpleCache<T> constructor(
        private val prefs: SharedPreferences,
        private val gson: Gson
) {

    protected abstract fun getKey(): String

    protected abstract fun getClassToDeserialize(): Class<T>

    fun put(t: T) {
        val serialized = gson.toJson(t)
        prefs.edit().putString(getKey(), serialized).apply()
    }

    fun get(): T? {
        val serialized = prefs.getString(getKey(), null)

        serialized?.apply {
            try {
                return gson.fromJson(this, getClassToDeserialize())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return null
    }

}