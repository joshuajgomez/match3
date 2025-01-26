package com.joshgm3z.match3.model

import org.junit.Before
import org.junit.Test

class GameEngineTest {

    private lateinit var gameEngine: GameEngine

    @Before
    fun setUp() {
        gameEngine = GameEngine()
    }

    @Test
    fun move() {
        printGame()
        gameEngine.move(1, 2)
        printGame()
    }

    @Test
    fun removeItems() {
        printGame()
        gameEngine.move(2, 6)
        printGame()
        gameEngine.removeItems(listOf(2, 4, 5))
        printGame()
    }

    private fun printGame() {
        val items = gameEngine.listItems()
        items.forEachIndexed { index, item ->
            print("${item?.letter ?: "?"}  ")
            if ((index + 1) % 10 == 0) {
                println()
            }
        }
    }
}