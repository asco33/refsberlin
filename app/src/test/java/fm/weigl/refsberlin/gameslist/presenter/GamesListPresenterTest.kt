package fm.weigl.refsberlin.gameslist.presenter

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.then
import fm.weigl.refsberlin.TestGames
import fm.weigl.refsberlin.TestGames.Companion.placeName
import fm.weigl.refsberlin.base.LoadingState
import fm.weigl.refsberlin.base.UINavigator
import fm.weigl.refsberlin.calendar.CalendarEventCreator
import fm.weigl.refsberlin.error.view.IErrorScreen
import fm.weigl.refsberlin.gameslist.net.GamesRepository
import fm.weigl.refsberlin.gameslist.view.IGamesListView
import fm.weigl.refsberlin.rx.TestSchedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
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
    @Mock lateinit var errorScreen: IErrorScreen
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
        classToTest.errorScreen = errorScreen
    }

    @Test
    fun displayGames() {

        given(gamesRepository.getGames()).willReturn(Observable.just(gamesList))
        given(view.getFilterText()).willReturn("")
        given(filter.filterGames(eq(gamesList), any())).willReturn(gamesList)

        classToTest.loadGames()

        verify(view).displayGames(gamesList)

    }

    @Test
    fun refreshesGames() {

        given(gamesRepository.getGames()).willReturn(Observable.just(gamesList))
        given(view.getFilterText()).willReturn("")
        given(filter.filterGames(eq(gamesList), any())).willReturn(gamesList)

        classToTest.refreshPulled()

        verify(view).displayGames(gamesList)

    }

    @Test
    fun filtersGamesWhenFilterTextChanged() {

        val filterText = "filter"
        given(view.getFilterText()).willReturn(filterText)
        given(gamesRepository.getGames()).willReturn(Observable.just(gamesList))

        classToTest.loadGames()

        given(filter.filterGames(gamesList, filterText)).willReturn(listOf(game2))

        classToTest.filterTextChanged()

        verify(view).displayGames(listOf(game2))

    }

    @Test
    fun filtersGamesAfterLoadingThem() {

        val filterText = "filter"
        given(filter.filterGames(gamesList, filterText)).willReturn(listOf(game2))
        given(view.getFilterText()).willReturn(filterText)
        given(gamesRepository.getGames()).willReturn(Observable.just(gamesList))

        classToTest.loadGames()

        verify(view).displayGames(listOf(game2))

    }

    @Test
    fun passesFilterTextAsHighlightToView() {

        val filterText = "text"
        given(view.getFilterText()).willReturn(filterText)

        classToTest.filterTextChanged()

        verify(view).highlightGamesWithText(filterText)
    }

    @Test
    fun createsCalendarEvent() {

        classToTest.eventIconClickedForGame(game1)

        verify(eventCreator).createEventForGame(game1)
    }

    @Test
    fun showsLoadingWhenStartsToLoad() {

        given(gamesRepository.getGames()).willReturn(Observable.just(gamesList))

        classToTest.loadGames()

        verify(view).setLoadingState(LoadingState.LOADING)

    }

    @Test
    fun showsRefreshingWhenStartsToLoadAgain() {

        given(gamesRepository.getGames()).willReturn(Observable.just(gamesList))

        classToTest.loadGames()

        classToTest.refreshPulled()

        verify(view).setLoadingState(LoadingState.REFRESHING)

    }

    @Test
    fun hidesLoadingWhenLoadingDone() {

        given(gamesRepository.getGames()).willReturn(Observable.just(gamesList))

        classToTest.loadGames()

        verify(view).setLoadingState(LoadingState.DONE)

    }

    @Test
    fun hidesLoadingOnErrorLoaded() {

        given(gamesRepository.getGames()).willReturn(Observable.error(Throwable()))

        classToTest.loadGames()

        verify(view).setLoadingState(LoadingState.DONE)

    }

    @Test
    fun showsError() {

        val error = Throwable()

        given(gamesRepository.getGames()).willReturn(Observable.error(error))

        classToTest.loadGames()

        then(errorScreen).should().showError(error.toString())

    }

    @Test
    fun showsNavigation() {

        classToTest.navigationIconClickedForGame(game1)

        verify(uiNavigator).showNavigationToLocation(placeName)

    }

}