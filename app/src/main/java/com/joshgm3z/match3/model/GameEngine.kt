package com.joshgm3z.match3.model

import com.joshgm3z.match3.ui.Item
import com.joshgm3z.match3.utils.getItems
import javax.inject.Inject

class GameEngine
@Inject constructor() {

    private val items: ArrayList<Item> = getItems() as ArrayList<Item>

    fun move(source: Int, target: Int) {
        println("Moving $source to $target")
        items.swap(source, target)
    }

    fun listItems(): List<Item> {
        println("Hello world")
        println("items = [$items]")

        return items
    }
}

private fun <E> ArrayList<E>.swap(
    source: Int,
    target: Int
) {
    val sourceTemp = this[source]
    val targetTemp = this[target]
    this[source] = targetTemp
    this[target] = sourceTemp
}
