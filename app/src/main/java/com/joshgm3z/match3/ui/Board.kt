package com.joshgm3z.match3.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joshgm3z.match3.ui.theme.Match3Theme
import com.joshgm3z.match3.utils.getItems
import kotlinx.coroutines.launch

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
fun Board(boardSize: Int = 400) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(10),
            modifier = Modifier.size(boardSize.dp),
        ) {
            items(getItems()) {
                Cell(it, boardSize / 10)
            }
        }
    }
}

@Composable
fun Cell(item: Item, cellSize: Int) {
    val scope = rememberCoroutineScope()
    val offset = remember {
        Animatable(initialValue = 0f)
    }
    val contextMenuWidth by remember { mutableFloatStateOf(0f) }
    LaunchedEffect(key1 = true, contextMenuWidth) {
        offset.animateTo(contextMenuWidth)
    }
    Icon(
        item.icon,
        contentDescription = null,
        tint = item.color,
        modifier = Modifier
            .size(cellSize.dp)
            .pointerInput(contextMenuWidth) {
                detectHorizontalDragGestures(
                    onHorizontalDrag = { _, dragAmount ->
                        scope.launch {
                            val newOffset = (offset.value + dragAmount)
                                .coerceIn(0f, contextMenuWidth)
                            offset.snapTo(newOffset)
                        }
                    },
                    onDragEnd = {

                    },
                )
            }
    )
}
