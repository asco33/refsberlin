package fm.weigl.refsberlin.gameslist.view

import android.databinding.ObservableField
import android.text.Spannable
import android.view.View
import fm.weigl.refdata.Game
import fm.weigl.refsberlin.R
import fm.weigl.refsberlin.android.ContextCompatWrapper
import fm.weigl.refsberlin.gameslist.presenter.GameInfoFormatter
import fm.weigl.refsberlin.view.TextHighlighter
import javax.inject.Inject

/**
 * Created by asco on 15.07.16.
 */

class GameViewModel @Inject constructor(private val textHighlighter: TextHighlighter,
                                        private val contextCompat: ContextCompatWrapper,
                                        private val gameInfoFormatter: GameInfoFormatter) {

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

    var onEventIconClick: () -> Unit = {}
    var onNavigationIconClick: () -> Unit = {}

    fun setGameAndTextToHighlight(game: Game, highlightText: String) {

        val highlightColor = contextCompat.getColor(R.color.colorAccent)
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
        game.referees.getOrNull(0)?.apply {
            ref0.set(textHighlighter.highlightText(gameInfoFormatter.refereeWithPosition(this)))
        } ?: ref0.set(textHighlighter.highlightText(""))

        game.referees.getOrNull(1)?.apply {
            ref1.set(textHighlighter.highlightText(gameInfoFormatter.refereeWithPosition(this)))
        } ?: ref1.set(textHighlighter.highlightText(""))

        game.referees.getOrNull(2)?.apply {
            ref2.set(textHighlighter.highlightText(gameInfoFormatter.refereeWithPosition(this)))
        } ?: ref2.set(textHighlighter.highlightText(""))

        game.referees.getOrNull(3)?.apply {
            ref3.set(textHighlighter.highlightText(gameInfoFormatter.refereeWithPosition(this)))
        } ?: ref4.set(textHighlighter.highlightText(""))

        game.referees.getOrNull(4)?.apply {
            ref4.set(textHighlighter.highlightText(gameInfoFormatter.refereeWithPosition(this)))
        } ?: ref4.set(textHighlighter.highlightText(""))

        game.referees.getOrNull(5)?.apply {
            ref5.set(textHighlighter.highlightText(gameInfoFormatter.refereeWithPosition(this)))
        } ?: ref5.set(textHighlighter.highlightText(""))

        game.referees.getOrNull(6)?.apply {
            ref6.set(textHighlighter.highlightText(gameInfoFormatter.refereeWithPosition(this)))
        } ?: ref6.set(textHighlighter.highlightText(""))

        game.referees.getOrNull(7)?.apply {
            ref7.set(textHighlighter.highlightText(gameInfoFormatter.refereeWithPosition(this)))
        } ?: ref7.set(textHighlighter.highlightText(""))

    }

    fun eventIconClicked(view: View) = onEventIconClick()

    fun navigationIconClicked(view: View) = onNavigationIconClick()

}