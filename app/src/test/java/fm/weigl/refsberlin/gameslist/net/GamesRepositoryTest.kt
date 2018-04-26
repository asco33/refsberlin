package fm.weigl.refsberlin.gameslist.net

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import fm.weigl.refdata.games.Game
import fm.weigl.refdata.games.Games
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Test

class GamesRepositoryTest {

    val gamesService: GamesService = mock()
    val gamesCache: GamesCache = mock()
    val gamesFromNetwork = listOf(mock<Game>())
    val gamesFromCache = listOf(mock<Game>())
    val networkResponse = Games(gamesFromNetwork)
    val cacheResponse = Games(gamesFromCache)
    val classToTest = GamesRepository(gamesService, gamesCache)

    @Test
    fun fetchesGamesFromNetwork() {

        given(gamesService.getGames()).willReturn(Single.just(networkResponse))

        classToTest.getGames(false)
                .test()
                .assertValue(gamesFromNetwork)
                .assertNoErrors()
                .assertComplete()

    }

    @Test
    fun fetchesGamesFromCache() {

        given(gamesService.getGames()).willReturn(Single.error(Throwable()))
        given(gamesCache.get()).willReturn(cacheResponse)

        classToTest.getGames(true)
                .test()
                .assertValue(gamesFromCache)
                .assertNoErrors()
                .assertComplete()

    }

    @Test
    fun fetchesGamesFromNetworkAndCache() {

        given(gamesService.getGames()).willReturn(Single.just(networkResponse))
        given(gamesCache.get()).willReturn(cacheResponse)

        classToTest.getGames(true)
                .test()
                .assertValues(gamesFromCache, gamesFromNetwork)
                .assertNoErrors()
                .assertComplete()

    }
}