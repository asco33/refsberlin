package fm.weigl.refsberlin.gameslist.view

import android.support.v7.widget.RecyclerView
import fm.weigl.refdata.games.Game
import fm.weigl.refsberlin.databinding.GameItemBinding

class GameViewHolder(
        private val binding: GameItemBinding,
        private val viewModel: GameViewModel) : RecyclerView.ViewHolder(binding.root
) {

    fun setGameAndHighlightText(game: Game, highLightText: String) {
        viewModel.setGameAndTextToHighlight(game, highLightText)
        binding.game = viewModel
    }

    fun onEventIconClick(onClick: () -> Unit) {
        viewModel.onEventIconClick = onClick
    }

    fun onNavigationIconClick(onClick: () -> Unit) {
        viewModel.onNavigationIconClick = onClick
    }
}