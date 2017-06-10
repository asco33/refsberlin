package fm.weigl.refsberlin.gameslist.presenter

import android.util.Log
import fm.weigl.refdata.Game
import fm.weigl.refsberlin.base.UINavigator
import fm.weigl.refsberlin.calendar.CalendarEventCreator
import fm.weigl.refsberlin.di.ActivityScope
import fm.weigl.refsberlin.error.view.ErrorScreenDelegate
import fm.weigl.refsberlin.error.view.IErrorScreen
import fm.weigl.refsberlin.gameslist.net.GamesRepository
import fm.weigl.refsberlin.gameslist.view.GamesListEventDelegate
import fm.weigl.refsberlin.gameslist.view.IGamesListView
import fm.weigl.refsberlin.rx.Schedulers
import javax.inject.Inject

/**
 * Created by asco on 15.07.16.
 */

@ActivityScope
class GamesListPresenter @Inject constructor(private val gamesRepository: GamesRepository,
                                             private val schedulers: Schedulers,
                                             private val filter: GamesFilter,
                                             private val eventCreator: CalendarEventCreator,
                                             private val uiNavigator: UINavigator)
    : GamesListEventDelegate, ErrorScreenDelegate {

    companion object {
        const val TAG = "GamesListPresenter"
    }

    lateinit var view: IGamesListView
    lateinit var errorScreen: IErrorScreen
    private var games = listOf<Game>()

    fun loadGames() {

        view.setLoading(true)
        errorScreen.hideError()

        gamesRepository.getGames()
                .subscribeOn(schedulers.new())
                .observeOn(schedulers.main())
                .subscribe({

                    this.games = it
                    view.displayGames(it)
                    view.setLoading(false)

                },
                        {
                            Log.e(TAG, it.toString())
                            it.printStackTrace()
                            errorScreen.showError(it.toString())
                            view.setLoading(false)
                        })


    }

    override fun filterTextChanged(filterText: String) {
        view.displayGames(filter.filterGames(games, filterText))
        view.highlightGamesWithText(filterText)
    }

    override fun eventIconClickedForGame(game: Game) = eventCreator.createEventForGame(game)

    override fun navigationIconClickedForGame(game: Game) = uiNavigator.showNavigationToLocation(game.place.place)

    override fun retryClicked() = loadGames()
}