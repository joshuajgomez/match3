package com.joshgm3z.match3.model

import com.joshgm3z.match3.model.candy.Candy
import com.joshgm3z.match3.model.candy.Empty
import com.joshgm3z.match3.utils.randomCandy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameEngine
@Inject constructor() {

    /**
     * Moves one item from source to target
     */
    fun move(candies: List<Candy>, source: Int, target: Int): List<Candy> {
        println("Moving $source to $target")
        return candies.swap(source, target).reposition()
    }

    fun removeItems(candies: List<Candy>, list: List<Int>): List<Candy> {
        println("Removing $list")
        return candies.map {
            when {
                list.contains(it.position) -> Empty()
                else -> it
            }
        }
    }

    /**
     * Fill empty cells with random items
     */
    fun fillEmptyCells(candies: List<Candy>): List<Candy> {
        println("Filling empty cells")
        val emptyCells = candies.mapIndexedNotNull { index, it ->
            when {
                it is Empty -> index
                else -> null
            }
        }
        var candiesTemp = candies
        emptyCells.forEach {
            candiesTemp = shiftDown(candiesTemp, it)
        }
        return candies.map {
            when {
                it is Empty -> randomCandy()
                else -> it
            }
        }.reposition()
    }

    private fun shiftDown(candies: List<Candy>, index: Int): List<Candy> {
        var counter = index
        var shifted = candies
        while (counter >= 10) {
            shifted = shifted.swap(counter, counter - 10)
            counter -= 10
        }
        return shifted
    }

    /**
     * Checks if match of 3 or more items exist
     * First searches all rows, then columns
     */
    fun check3Match(candies: List<Candy>): List<Int> {
        var match = listOf<Int>()
        run loop@{
            candies.forEachRow { it ->
                check3MatchPerList(it).let {
                    if (it.isNotEmpty()) {
                        match = it
                        return@loop
                    }
                }
            }
            if (match.isNotEmpty()) return match
            candies.forEachCol { it ->
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

}
