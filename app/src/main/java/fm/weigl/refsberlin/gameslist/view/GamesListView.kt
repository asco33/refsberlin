package fm.weigl.refsberlin.gameslist.view

import android.app.Activity
import android.content.res.Resources
import android.databinding.ObservableBoolean
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import fm.weigl.refdata.Game
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.android.Toaster
import fm.weigl.refsberlin.di.ActivityScope
import javax.inject.Inject

/**
 * Created by asco on 4/20/17.
 */

interface IGamesListView {
    fun displayGames(games: List<Game>)
    fun highlightGamesWithText(text: String)
    fun setLoading(loading: Boolean)
    fun getFilterText(): String
}

@ActivityScope
class GamesListView @Inject constructor(
        private val adapter: GamesListAdapter,
        private val activity: Activity,
        private val toaster: Toaster,
        private val resources: Resources
) : IGamesListView {

    val loading = ObservableBoolean(false)

    lateinit var editText: EditText

    var eventDelegate: GamesListEventDelegate? = null
        set(value) {
            field = value
            adapter.eventDelegate = value
        }

    fun setViews(recyclerView: RecyclerView, editText: EditText) {

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        this.editText = editText

    }

    fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
        eventDelegate?.filterTextChanged()
    }

    override fun displayGames(games: List<Game>) {
        adapter.games = games
        toaster.showToast(resources.getString(R.string.number_of_games, games.size))
    }

    override fun highlightGamesWithText(text: String) {
        adapter.highlightText = text
    }

    override fun setLoading(loading: Boolean) = this.loading.set(loading)

    override fun getFilterText(): String = editText.text.toString().trim()
}