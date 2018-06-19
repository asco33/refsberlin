package fm.weigl.refsberlin.gameslist.presenter

import fm.weigl.refdata.games.Game
import fm.weigl.refsberlin.base.LoadingState
import fm.weigl.refsberlin.base.UINavigator
import fm.weigl.refsberlin.calendar.CalendarEventCreator
import fm.weigl.refsberlin.di.ActivityScope
import fm.weigl.refsberlin.error.view.ErrorScreenDelegate
import fm.weigl.refsberlin.error.view.IErrorScreen
import fm.weigl.refsberlin.gameslist.net.GamesRepository
import fm.weigl.refsberlin.gameslist.view.GamesListEventDelegate
import fm.weigl.refsberlin.gameslist.view.IGamesListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScope
class GamesListPresenter @Inject constructor(private val gamesRepository: GamesRepository,
                                             private val filter: GamesFilter,
                                             private val eventCreator: CalendarEventCreator,
                                             private val uiNavigator: UINavigator)
    : GamesListEventDelegate, ErrorScreenDelegate {

    lateinit var view: IGamesListView
    lateinit var errorScreen: IErrorScreen
    private var games = listOf<Game>()

    fun loadGames(acceptCached: Boolean) {

        val loadingState = if (games.isEmpty()) LoadingState.LOADING else LoadingState.REFRESHING

        view.setLoadingState(loadingState)
        errorScreen.hideError()

        gamesRepository.getGames(acceptCached)
                .distinct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    this.games = it
                    view.setLoadingState(LoadingState.DONE)
                    showFilteredGames()

                },
                        {
                            it.printStackTrace()
                            if (view.isEmpty()) errorScreen.showMajorError(it.toString()) else errorScreen.showMinorError()
                            view.setLoadingState(LoadingState.DONE)
                        })


    }

    override fun filterTextChanged() = showFilteredGames()

    override fun eventIconClickedForGame(game: Game) = eventCreator.createEventForGame(game)

    override fun navigationIconClickedForGame(game: Game) = uiNavigator.showNavigationToLocation(game.place.place)

    override fun refreshPulled() = loadGames(false)

    override fun retryClicked() = loadGames(true)

    private fun showFilteredGames() {
        val filterText = view.getFilterText()
        view.displayGames(filter.filterGames(games, filterText))
        view.highlightGamesWithText(filterText)
    }
}