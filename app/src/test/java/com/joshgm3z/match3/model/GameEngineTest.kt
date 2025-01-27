package com.joshgm3z.match3.model

import com.joshgm3z.match3.model.candy.Empty
import com.joshgm3z.match3.utils.getCandies
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

class GameEngineTest {

    private lateinit var gameEngine: GameEngine
    private var candies = getCandies()

    @Before
    fun setUp() {
        gameEngine = GameEngine()
        candies.reposition()
    }

    @After
    fun tearDown() {
        printCandies()
    }

    private fun printCandies() {
        printCandies(candies)
    }

    @Test
    fun move() {
        val source = 1
        val target = 2
        val before = candies
        candies = gameEngine.move(candies, source, target)
        Assert.assertEquals(before[source], candies[target])
        Assert.assertEquals(before[target], candies[source])
    }

    @Test
    fun removeItems() {
        val list = listOf(2, 4, 5)
        candies = gameEngine.removeItems(candies, list)
        candies.forEach {
            when {
                list.contains(it.position) -> assert(it is Empty)
                else -> assert(it !is Empty)
            }
        }
    }

    @Test
    fun `fillEmptyCells horizontal`() {
        val list = listOf(95, 96, 97)
        var temp = list
        val before = candies
        candies = gameEngine.removeItems(candies, list)
        printCandies()
        candies = gameEngine.fillEmptyCells(candies)

        while (temp[0] > 10) {
            temp.forEach { assertEquals(before[it - 10], candies[it]) }
            temp = temp.map { it - 10 }
        }
        temp.forEach { assert(candies[it] !is Empty) }
    }

    @Test
    fun `fillEmptyCells vertical`() {
        val list = listOf(43, 33, 23)
        candies = gameEngine.removeItems(candies, list)
        printCandies()
        candies = gameEngine.fillEmptyCells(candies)

        list.forEach { assert(candies[it] !is Empty) }
    }

}