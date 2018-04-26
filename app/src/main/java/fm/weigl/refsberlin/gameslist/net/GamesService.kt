package fm.weigl.refsberlin.gameslist.net

import fm.weigl.refdata.games.Games
import io.reactivex.Single
import retrofit2.http.GET

interface GamesService {
    @GET("/games")
    fun getGames(): Single<Games>

}