package com.joshgm3z.match3.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.joshgm3z.match3.model.candy.Candy
import com.joshgm3z.match3.ui.theme.Match3Theme
import com.joshgm3z.match3.utils.Logger
import com.joshgm3z.match3.utils.getCandies
import kotlin.math.roundToInt

@Preview
@Composable
private fun PreviewBoard() {
    Match3Theme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Board()
        }
    }
}

@Composable
fun BoardContainer(viewModel: GameViewModel = hiltViewModel()) {
    with(viewModel.uiState.collectAsState().value) {
        Board(
            items = candies,
            onMove = { x, y -> viewModel.onMove(x, y) })
    }
}

@Composable
fun Board(
    boardSize: Int = 400,
    items: List<Candy> = getCandies(),
    onMove: (source: Int, target: Int) -> Unit = { _, _ -> },
) {
    Logger.debug("Board=[$items]")
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(10),
            modifier = Modifier.size(boardSize.dp),
        ) {
            items(items) {
                Cell(it, boardSize / 10, onMove)
            }
        }
    }
}

@Composable
fun Cell(
    candy: Candy, cellSize: Int,
    onMove: (source: Int, target: Int) -> Unit,
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    val modifier = Modifier
        .size(cellSize.dp)
        .offset {
            IntOffset(
                offsetX.roundToInt(),
                offsetY.roundToInt()
            )
        }
        .pointerInput(
            Unit, pointerAction(
                offsetX,
                offsetY,
                onOffsetChanged = { x, y ->
                    /*offsetX = x
                    offsetY = y*/
                },
                onSwipe = { direction ->
                    Logger.debug("direction=$direction, position=${candy.position}")
                    with(candy) {
                        when (direction) {
                            DragDirection.Left -> onMove(position, position - 1)
                            DragDirection.Right -> onMove(position, position + 1)
                            DragDirection.Up -> onMove(position, position - 10)
                            DragDirection.Down -> onMove(position, position + 10)
                            else -> {}
                        }
                    }
                }
            )
        )
        .background(
            when (candy.highLight) {
                true -> Color.LightGray
                else -> Color.White
            }
        )
    Icon(
        candy.icon,
        contentDescription = null,
        tint = candy.color,
        modifier = modifier
    )
}

enum class DragDirection {
    None, Left, Right, Up, Down
}

fun pointerAction(
    offsetX: Float,
    offsetY: Float,
    onOffsetChanged: (Float, Float) -> Unit,
    onSwipe: (DragDirection) -> Unit,
): suspend PointerInputScope.() -> Unit = {
    var dragDirection = DragDirection.None
    detectDragGestures(
        onDrag = { change, dragAmount ->
            change.consume()
            val (x, y) = dragAmount
            onOffsetChanged(offsetX + dragAmount.x, offsetY + dragAmount.y)
            dragDirection = when {
                x > 0 -> DragDirection.Right /* right */
                x < 0 -> DragDirection.Left /* left */
                else -> DragDirection.None
            }
            if (dragDirection != DragDirection.None) return@detectDragGestures
            dragDirection = when {
                y > 0 -> DragDirection.Down /* down */
                y < 0 -> DragDirection.Up /* up */
                else -> DragDirection.None
            }
        },
        onDragStart = {},
        onDragEnd = {
            onSwipe(dragDirection)
            onOffsetChanged(0f, 0f)
        },
        onDragCancel = {}
    )

}