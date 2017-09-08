package fm.weigl.refsberlin.gameslist.view

import android.app.Activity
import android.content.res.Resources
import android.databinding.ObservableField
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import fm.weigl.refdata.Game
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.android.Toaster
import fm.weigl.refsberlin.base.LoadingState
import fm.weigl.refsberlin.di.ActivityScope
import javax.inject.Inject

interface IGamesListView {
    fun displayGames(games: List<Game>)
    fun highlightGamesWithText(text: String)
    fun setLoadingState(state: LoadingState)
    fun getFilterText(): String
}

@ActivityScope
class GamesListView @Inject constructor(
        private val adapter: GamesListAdapter,
        private val activity: Activity,
        private val toaster: Toaster,
        private val resources: Resources
) : IGamesListView {

    val loadingState = ObservableField<LoadingState>()

    private lateinit var editText: EditText

    var delegate: GamesListEventDelegate? = null
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
        delegate?.filterTextChanged()
    }

    fun clearButtonClicked() = editText.setText("")

    fun onRefresh() = delegate?.refreshPulled()

    override fun displayGames(games: List<Game>) {
        adapter.games = games
        toaster.showToast(resources.getString(R.string.number_of_games, games.size))
    }

    override fun highlightGamesWithText(text: String) {
        adapter.highlightText = text
    }

    override fun setLoadingState(state: LoadingState) = loadingState.set(state)

    override fun getFilterText(): String = editText.text.toString().trim()
}