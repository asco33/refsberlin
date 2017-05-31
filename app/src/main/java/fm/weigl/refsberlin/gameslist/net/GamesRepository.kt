package fm.weigl.refsberlin.gameslist.net

import fm.weigl.refdata.Game
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by asco on 15.07.16.
 */

@Singleton
class GamesRepository @Inject constructor(private val gamesService: GamesService) {

    fun getGames(): Observable<List<Game>> {
        return gamesService.listGames().map { it.games }
    }

}