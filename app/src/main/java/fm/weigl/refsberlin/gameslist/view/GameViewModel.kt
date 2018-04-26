package fm.weigl.refsberlin.gameslist.view

import android.databinding.ObservableField
import android.text.Spannable
import android.view.View
import fm.weigl.refdata.games.Game
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.android.ContextCompatWrapper
import fm.weigl.refsberlin.gameslist.presenter.GameInfoFormatter
import fm.weigl.refsberlin.view.TextHighlighter
import javax.inject.Inject

class GameViewModel @Inject constructor(contextCompat: ContextCompatWrapper,
                                        private val textHighlighter: TextHighlighter,
                                        private val gameInfoFormatter: GameInfoFormatter) {

    val highlightColor = contextCompat.getColor(R.color.colorAccent)

    val teams = ObservableField<Spannable>()
    val date = ObservableField<String>()
    val location = ObservableField<Spannable>()
    val ref0 = ObservableField<Spannable>()
    val ref1 = ObservableField<Spannable>()
    val ref2 = ObservableField<Spannable>()
    val ref3 = ObservableField<Spannable>()
    val ref4 = ObservableField<Spannable>()
    val ref5 = ObservableField<Spannable>()
    val ref6 = ObservableField<Spannable>()
    val ref7 = ObservableField<Spannable>()
    val ref8 = ObservableField<Spannable>()
    val ref9 = ObservableField<Spannable>()
    val ref10 = ObservableField<Spannable>()
    val ref11 = ObservableField<Spannable>()
    val ref12 = ObservableField<Spannable>()
    val ref13 = ObservableField<Spannable>()
    val ref14 = ObservableField<Spannable>()

    var onEventIconClick: () -> Unit = {}
    var onNavigationIconClick: () -> Unit = {}

    fun setGameAndTextToHighlight(game: Game, highlightText: String) {

        textHighlighter.color = highlightColor
        textHighlighter.toHighlight = highlightText

        // Teams
        val teamsText = gameInfoFormatter.teams(game)
        teams.set(textHighlighter.highlightText(teamsText))

        // Date
        date.set(gameInfoFormatter.date(game))

        // Place
        location.set(textHighlighter.highlightText(game.place.place))

        // Referees
        ref0.set(getRefName(game, 0))
        ref1.set(getRefName(game, 1))
        ref2.set(getRefName(game, 2))
        ref3.set(getRefName(game, 3))
        ref4.set(getRefName(game, 4))
        ref5.set(getRefName(game, 5))
        ref6.set(getRefName(game, 6))
        ref7.set(getRefName(game, 7))
        ref8.set(getRefName(game, 8))
        ref9.set(getRefName(game, 9))
        ref10.set(getRefName(game, 10))
        ref11.set(getRefName(game, 11))
        ref12.set(getRefName(game, 12))
        ref13.set(getRefName(game, 13))
        ref14.set(getRefName(game, 14))

    }

    fun eventIconClicked() = onEventIconClick()

    fun navigationIconClicked() = onNavigationIconClick()

    private fun getRefName(game: Game, index: Int): Spannable? {

        val ref = game.referees.getOrNull(index)
        if (ref == null) return null
        else return textHighlighter.highlightText(gameInfoFormatter.refereeWithPosition(ref))

    }

}