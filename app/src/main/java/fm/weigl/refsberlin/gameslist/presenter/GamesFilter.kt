package fm.weigl.refsberlin.gameslist.presenter

import fm.weigl.refdata.games.Game
import javax.inject.Inject

class GamesFilter @Inject constructor() {


    fun filterGames(games: List<Game>, filterText: String): List<Game> {

        if (filterText.trim().isBlank()) {
            return games
        }

        return games.filter {

            it.homeTeam.name.contains(filterText, ignoreCase = true) ||
                    it.awayTeam.name.contains(filterText, ignoreCase = true) ||
                    it.referees.any { it.name.contains(filterText, ignoreCase = true) } ||
                    it.place.place.contains(filterText, ignoreCase = true)

        }

    }

}