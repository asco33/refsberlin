package fm.weigl.refsberlin.gameslist.net

import android.content.SharedPreferences
import com.google.gson.Gson
import fm.weigl.cache.SimpleCache
import fm.weigl.refdata.games.Games
import javax.inject.Inject

class GamesCache @Inject constructor(prefs: SharedPreferences, gson: Gson) : SimpleCache<Games>(prefs, gson) {

    private val key = "GAMES"

    override fun getKey() = key

    override fun getClassToDeserialize() = Games::class.java
}