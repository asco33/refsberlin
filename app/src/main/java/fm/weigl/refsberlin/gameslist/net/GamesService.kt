package fm.weigl.refsberlin.gameslist.net

import fm.weigl.refdata.Games
import rx.Observable

interface GamesService {
    @retrofit.http.GET("/hallo")
    fun listGames(): Observable<Games>

}