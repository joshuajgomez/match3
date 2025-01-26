package com.joshgm3z.match3.model

import com.joshgm3z.match3.ui.Item
import com.joshgm3z.match3.utils.getItems
import javax.inject.Inject

class GameEngine
@Inject constructor() {

    private var items: ArrayList<Item?> = getItems()

    init {
        reposition()
    }

    private fun reposition() = items.forEachIndexed { index, item ->
        item?.position = index
    }

    /**
     * Moves one item from source to target
     */
    fun move(source: Int, target: Int) {
        println("Moving $source to $target")
        items.swap(source, target)
        reposition()
    }

    fun removeItems(list: List<Int>) {
        println("Removing $list")
        list.forEach {
            items[it] = null
        }
    }

    /**
     * Fill empty cells with random items
     */
    fun fillEmptyCells() {
        println("Filling empty cells")
        val nullIndices = items.mapIndexedNotNull { index, item ->
            when {
                item == null -> index
                else -> null
            }
        }
        nullIndices.forEach {
            shiftDown(it)
        }
        println("shifted")
        printGame(items)
        println("filled")
        items = items.map {
            when {
                it == null -> getItems().random()
                else -> it
            }
        }.toArrayList()
        reposition()
    }

    private fun shiftDown(index: Int) {
        var counter = index
        while (counter >= 10) {
            items.swap(counter, counter - 10)
            counter -= 10
        }
    }

    /**
     * Checks if match of 3 or more items exist
     * First searches all rows, then columns
     */
    private fun check3Match(): List<Int> {
        var match = listOf<Int>()
        run loop@{
            items.forEachRow { it ->
                check3MatchPerList(it).let {
                    if (it.isNotEmpty()) {
                        match = it
                        return@loop
                    }
                }
            }
            if (match.isNotEmpty()) return match
            items.forEachCol { it ->
                check3MatchPerList(it).let {
                    if (it.isNotEmpty()) {
                        match = it
                        return@loop
                    }
                }
            }
        }
        return match
    }

    fun listItems(): List<Item?> {
        return items
    }

}
