package fm.weigl.refsberlin.gameslist.net

import fm.weigl.refdata.Games
import rx.Observable

/**
 * Created by asco on 15.07.16.
 */

interface GamesService {
    @retrofit.http.GET("/hallo")
    fun listGames(): Observable<Games>

}