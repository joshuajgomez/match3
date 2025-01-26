package com.joshgm3z.match3.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joshgm3z.match3.model.candy.Candy
import com.joshgm3z.match3.model.GameEngine
import com.joshgm3z.match3.model.reposition
import com.joshgm3z.match3.utils.Logger
import com.joshgm3z.match3.utils.getCandies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BoardState(
    var candies: List<Candy>,
    val overlayText: String,
)

@HiltViewModel
class GameViewModel
@Inject constructor(
    private val gameEngine: GameEngine,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        BoardState(
            getCandies().reposition(),
            "New game"
        )
    )
    val uiState = _uiState.asStateFlow()

    private var lastMove: Pair<Int, Int> = Pair(0, 0)

    init {
        checkMatch()
    }

    private fun checkMatch() {
        val matches = gameEngine.check3Match(_uiState.value.candies)
        when {
            matches.isEmpty() -> when {
                lastMove.first == 0 && lastMove.second == 0 -> {} // new game
                else -> {
                    // revert
                    viewModelScope.launch {
                        delay(1000)
                        _uiState.update {
                            it.copy(
                                candies = gameEngine.move(
                                    it.candies,
                                    lastMove.second,
                                    lastMove.first
                                )
                            )
                        }
                    }
                }
            }

            else -> {
                highLightMatch(matches)
            }
        }
    }

    private fun removeHighlight() {
        _uiState.update { it ->
            it.copy(
                candies = it.candies.map {
                    it.highLight = false
                    it
                }
            )
        }
    }

    private fun highLightMatch(matches: List<Int>) {
        Logger.debug("matches = [${matches}]")
        _uiState.update { it ->
            it.copy(
                candies = it.candies.map {
                    if (matches.contains(it.position)) {
                        it.highLight = true
                    }
                    it
                }
            )
        }
    }

    /**
     * source and target are numbers between 1 and 25 indicating
     * positions on the board.
     */
    fun onMove(source: Int, target: Int) {
        Logger.debug("source = [${source}], target = [${target}]")
        lastMove = Pair(source, target)
        _uiState.update {
            it.copy(
                candies = gameEngine.move(it.candies, source, target)
            )
        }
        checkMatch()
    }
}