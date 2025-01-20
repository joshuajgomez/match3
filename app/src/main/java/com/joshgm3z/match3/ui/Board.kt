package com.joshgm3z.match3.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joshgm3z.match3.ui.theme.Match3Theme
import com.joshgm3z.match3.utils.getItems

@Preview
@Composable
private fun PreviewBoard() {
    Match3Theme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Board()
        }
    }
}

data class Item(
    val icon: ImageVector,
    val color: Color,
)

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
    Icon(
        item.icon,
        contentDescription = null,
        tint = item.color,
        modifier = Modifier.size(cellSize.dp)
    )
}
