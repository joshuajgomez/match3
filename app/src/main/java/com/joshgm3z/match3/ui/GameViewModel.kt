package com.joshgm3z.match3.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.joshgm3z.match3.model.GameEngine
import com.joshgm3z.match3.utils.getItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class Item(
    val icon: ImageVector,
    val letter: String,
    val color: Color,
    var position: Int = -1,
)

class BoardData(
    val items: List<Item?>,
    val overlayText: String,
)

@HiltViewModel
class GameViewModel
@Inject constructor(
    private val gameEngine: GameEngine
) : ViewModel() {

    private val _uiState = MutableStateFlow(BoardData(getItems(), "New game"))
    val uiState = _uiState.asStateFlow()

    /**
     * source and target are numbers between 1 and 25 indicating
     * positions on the board.
     */
    fun onMove(source: Int, target: Int) {

    }
}