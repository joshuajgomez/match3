package com.joshgm3z.match3.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.rounded.AcUnit
import androidx.compose.material.icons.rounded.Adb
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.ui.graphics.Color
import com.joshgm3z.match3.ui.Item

val item1 = Item(Icons.Default.ArrowBack, "A", Color.Red)
val item2 = Item(Icons.Rounded.ArrowDownward, "B", Color.Cyan)
val item3 = Item(Icons.Default.ArrowUpward, "C", Color.Black)
val item4 = Item(Icons.Rounded.Adb, "D", Color.Green)
val item5 = Item(Icons.Rounded.AcUnit, "E", Color.Blue)

fun getItems() = listOf(
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
    item1, item2, item3, item4, item5,
).mapIndexed { index, item ->
    item.copy(position = index)
}
