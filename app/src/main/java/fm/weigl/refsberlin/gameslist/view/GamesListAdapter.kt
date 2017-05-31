package fm.weigl.refsberlin.gameslist.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import fm.weigl.refdata.Game
import fm.weigl.refsberlin.databinding.GameItemBinding
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by asco on 15.07.16.
 */

interface GamesListEventDelegate {
    fun eventIconClickedForGame(game: Game)
    fun navigationIconClickedForGame(game: Game)
    fun filterTextChanged(filterText: String)
}

class GamesListAdapter @Inject constructor(
        private val viewModelProvider: Provider<GameViewModel>
) : RecyclerView.Adapter<GameViewHolder>() {

    var eventDelegate: GamesListEventDelegate? = null

    var games = listOf<Game>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var highlightText = ""
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GameViewHolder {

        val inflater = LayoutInflater.from(parent?.context)

        val binding = GameItemBinding.inflate(inflater, parent, false)

        return GameViewHolder(binding, viewModelProvider.get())

    }

    override fun onBindViewHolder(holder: GameViewHolder?, position: Int) {

        holder?.setGameAndHighlightText(games.get(position), highlightText)

        holder?.onEventIconClick { eventDelegate?.eventIconClickedForGame(games[position]) }

        holder?.onNavigationIconClick { eventDelegate?.navigationIconClickedForGame(games[position]) }

    }
}