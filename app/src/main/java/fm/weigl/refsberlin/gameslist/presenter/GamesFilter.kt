package fm.weigl.refsberlin.gameslist.presenter

import fm.weigl.refdata.Game
import javax.inject.Inject

/**
 * Created by asco on 15.07.16.
 */
class GamesFilter @Inject constructor() {


    fun filterGames(games: List<Game>, filterText: String): List<Game> {

        if (filterText.trim().isBlank()) {
            return games
        }

        return games.filter {

            it.homeTeam.name.contains(filterText, ignoreCase = true) ||
                    it.awayTeam.name.contains(filterText, ignoreCase = true) ||
                    it.referees.filter { it.name.contains(filterText, ignoreCase = true) }.size > 0 ||
                    it.place.place.contains(filterText, ignoreCase = true)

        }

    }

}