package fm.weigl.refsberlin.gameslist.presenter

import android.content.res.Resources
import fm.weigl.refdata.Game
import fm.weigl.refdata.GameReferee
import fm.weigl.refsberlin.R
import java.text.SimpleDateFormat
import javax.inject.Inject

/**
 * Created by asco on 4/20/17.
 */
class GameInfoFormatter @Inject constructor(
        private val resources: Resources
) {

    val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")

    fun teams(game: Game) = resources.getString(R.string.match_representation, game.homeTeam.name, game.awayTeam.name)

    fun refereeWithPosition(referee: GameReferee) = resources.getString(R.string.referee_representation, referee.name, referee.position)

    fun date(game: Game) = dateFormat.format(game.date)

}