package fm.weigl.refsberlin.gameslist.net

import fm.weigl.refdata.games.Game
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GamesRepository @Inject constructor(private val gamesService: GamesService) {

    fun getGames(): Observable<List<Game>> = gamesService.listGames().map { it.games }

}