package fm.weigl.refsberlin.gameslist.view

import android.app.Activity
import android.databinding.ObservableBoolean
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import fm.weigl.refdata.Game
import fm.weigl.refsberlin.di.ActivityScope
import javax.inject.Inject

/**
 * Created by asco on 4/20/17.
 */

interface IGamesListView {
    fun displayGames(games: List<Game>)
    fun highlightGamesWithText(text: String)
    fun setLoading(loading: Boolean)
}

@ActivityScope
class GamesListView @Inject constructor(
        private val adapter: GamesListAdapter,
        private val activity: Activity
) : IGamesListView {

    val loading = ObservableBoolean(false)

    var eventDelegate: GamesListEventDelegate? = null
        set(value) {
            field = value
            adapter.eventDelegate = value
        }

    fun setViews(recyclerView: RecyclerView) {

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

    }

    fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
        eventDelegate?.filterTextChanged(text.toString().trim())
    }

    override fun displayGames(games: List<Game>) {
        adapter.games = games
    }

    override fun highlightGamesWithText(text: String) {
        adapter.highlightText = text
    }

    override fun setLoading(loading: Boolean) = this.loading.set(loading)


}