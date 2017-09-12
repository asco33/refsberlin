package fm.weigl.refsberlin.gameslist.net

import fm.weigl.refdata.games.Games
import rx.Observable

interface GamesService {
    @retrofit.http.GET("/games")
    fun listGames(): Observable<Games>

}