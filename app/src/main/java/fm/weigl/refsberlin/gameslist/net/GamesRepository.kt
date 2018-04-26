package fm.weigl.refsberlin.gameslist.net

import fm.weigl.refdata.games.Game
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GamesRepository @Inject constructor(private val gamesService: GamesService, private val cache: GamesCache) {

    fun getGames(acceptCached: Boolean): Observable<List<Game>> {
        val networkObservable = gamesService.getGames().toObservable()
                .doOnNext {
                    cache.put(it)
                }
                .map { it.games }

        if (acceptCached) {
            cache.get()?.apply {
                return networkObservable.startWith(this.games).onErrorResumeNext(Observable.empty())
            }
        }

        return networkObservable
    }

}