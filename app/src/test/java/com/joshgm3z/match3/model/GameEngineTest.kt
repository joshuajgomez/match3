package com.joshgm3z.match3.model

import com.joshgm3z.match3.utils.getCandies
import org.junit.After
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
        candies = gameEngine.move(candies, 1, 2)
    }

    @Test
    fun removeItems() {
        candies = gameEngine.move(candies, 2, 6)
        candies = gameEngine.removeItems(candies, listOf(2, 4, 5))
    }

    @Test
    fun fillEmptyCells() {
        candies = gameEngine.removeItems(candies, listOf(95, 96, 97))
        printCandies()
        candies = gameEngine.fillEmptyCells(candies)
    }

}