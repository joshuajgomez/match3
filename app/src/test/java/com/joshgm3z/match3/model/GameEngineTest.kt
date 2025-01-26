package com.joshgm3z.match3.model

import com.joshgm3z.match3.utils.getCandies
import org.junit.After
import org.junit.Before
import org.junit.Test

class GameEngineTest {

    private lateinit var gameEngine: GameEngine
    private val candies = getCandies()

    @Before
    fun setUp() {
        gameEngine = GameEngine()
    }

    @After
    fun tearDown() {
        candies.forEach {
            print(it)
        }
    }

    @Test
    fun move() {
        gameEngine.move(candies, 1, 2)
    }

    @Test
    fun removeItems() {
        gameEngine.move(candies, 2, 6)
        gameEngine.removeItems(candies, listOf(2, 4, 5))
    }

    @Test
    fun fillEmptyCells() {
        gameEngine.removeItems(candies, listOf(95, 96, 97))
        gameEngine.fillEmptyCells(candies)
    }

}