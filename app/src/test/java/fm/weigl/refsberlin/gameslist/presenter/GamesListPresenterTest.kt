package fm.weigl.refsberlin.gameslist.presenter

import com.nhaarman.mockito_kotlin.argThat
import fm.weigl.refsberlin.TestGames
import fm.weigl.refsberlin.TestGames.Companion.placeName
import fm.weigl.refsberlin.base.UINavigator
import fm.weigl.refsberlin.calendar.CalendarEventCreator
import fm.weigl.refsberlin.gameslist.net.GamesRepository
import fm.weigl.refsberlin.gameslist.view.IGamesListView
import fm.weigl.refsberlin.rx.TestSchedulers
import fm.weigl.refsberlin.view.SnackbarView
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import rx.Observable

@RunWith(MockitoJUnitRunner::class)
class GamesListPresenterTest {

    @Mock lateinit var gamesRepository: GamesRepository
    @Mock lateinit var filter: GamesFilter
    @Mock lateinit var view: IGamesListView
    @Mock lateinit var eventCreator: CalendarEventCreator
    @Mock lateinit var snackBar: SnackbarView
    @Mock lateinit var uiNavigator: UINavigator
    val schedulers = TestSchedulers()

    val game1 = TestGames.testGame
    val game2 = TestGames.testGame
    val gamesList = listOf(game1, game2)

    lateinit var classToTest: GamesListPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        classToTest = GamesListPresenter(gamesRepository, schedulers, filter, eventCreator, uiNavigator)
        classToTest.view = view
        classToTest.snackBar = snackBar
    }

    @Test
    fun displayGames() {

        given(gamesRepository.getGames()).willReturn(Observable.just(gamesList))

        classToTest.start()

        verify(view).displayGames(gamesList)

    }

    @Test
    fun filtersGamesWhenFilterTextChanged() {

        val filterString = "filter"
        given(gamesRepository.getGames()).willReturn(Observable.just(gamesList))
        given(filter.filterGames(gamesList, filterString)).willReturn(listOf(game2))

        Mockito.reset(view)
        classToTest.start()
        classToTest.filterTextChanged(filterString)

        verify(view).displayGames(listOf(game2))

    }

    @Test
    fun passesFilterTextAsHighlightToView() {

        val text = "text"

        classToTest.filterTextChanged(text)

        verify(view).highlightGamesWithText(text)
    }

    @Test
    fun createsCalendarEvent() {

        classToTest.eventIconClickedForGame(game1)

        verify(eventCreator).createEventForGame(game1)
    }

    @Test
    fun showsLoadingWhenStartsToLoad() {

        given(gamesRepository.getGames()).willReturn(Observable.just(gamesList))

        classToTest.start()

        verify(view).setLoading(true)

    }

    @Test
    fun hidesLoadingWhenLoadingDone() {

        given(gamesRepository.getGames()).willReturn(Observable.just(gamesList))

        classToTest.start()

        verify(view).setLoading(false)

    }

    @Test
    fun hidesLoadingOnErrorLoaded() {

        given(gamesRepository.getGames()).willReturn(Observable.error(Throwable()))

        classToTest.start()

        verify(view).setLoading(false)

    }

    @Test
    fun showsSnackbarOnError() {

        val errorMessage = "errorMessage"
        val error = Throwable(errorMessage)

        given(gamesRepository.getGames()).willReturn(Observable.error(error))

        classToTest.start()

        verify(snackBar).showSnackbar(argThat {
            this.contains(errorMessage) &&
                    this.contains(error.toString())
        })

    }

    @Test
    fun showsNavigation() {

        classToTest.navigationIconClickedForGame(game1)

        verify(uiNavigator).showNavigationToLocation(placeName)

    }

}