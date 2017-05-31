package fm.weigl.refsberlin.gameslist.net

import fm.weigl.refdata.Games
import fm.weigl.refsberlin.security.ApiKey
import retrofit2.http.Headers
import rx.Observable

/**
 * Created by asco on 15.07.16.
 */

interface GamesService {
    @retrofit.http.GET("/hallo")
    @Headers("Authorization:${ApiKey.API_KEY}")
    fun listGames(): Observable<Games>

}