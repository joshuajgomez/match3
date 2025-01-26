package com.joshgm3z.match3.model.candy

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Candy(
    val icon: ImageVector,
    val letter: String,
    var position: Int = -1,
    val color: Color,
    var highLight: Boolean = false,
) {
    override fun toString(): String {
        return "$letter.$position"
    }
}

data class Red(val id: String = "R") : Candy(
    icon = Icons.Filled.Star,
    letter = id,
    color = Color.Red,
) {
    override fun toString(): String {
        return "$letter.$position"
    }
}

data class Blue(val id: String = "B") : Candy(
    icon = Icons.Filled.Settings,
    letter = id,
    color = Color.Blue,
) {
    override fun toString(): String {
        return "$letter.$position"
    }
}

data class Yellow(val id: String = "Y") : Candy(
    icon = Icons.Filled.Save,
    letter = id,
    color = Color.Yellow,
) {
    override fun toString(): String {
        return "$letter.$position"
    }
}

data class Green(val id: String = "G") : Candy(
    icon = Icons.Filled.Search,
    letter = id,
    color = Color.Green,
) {
    override fun toString(): String {
        return "$letter.$position"
    }
}

data class Purple(val id: String = "P") : Candy(
    icon = Icons.Filled.CameraAlt,
    letter = "P",
    color = Color.Magenta,
) {
    override fun toString(): String {
        return "$letter.$position"
    }
}

data class Empty(val id: String = "?") : Candy(
    icon = Icons.Filled.Clear,
    letter = id,
    color = Color.White,
) {
    override fun toString(): String {
        return "$letter.$position"
    }
}
