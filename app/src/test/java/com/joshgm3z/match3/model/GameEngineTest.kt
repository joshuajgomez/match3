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
        print()
        gameEngine.move(1, 2)
        print()
    }

    @Test
    fun removeItems() {
        print()
        gameEngine.move(2, 6)
        print()
        gameEngine.removeItems(listOf(2, 4, 5))
        print()
    }

    @Test
    fun fillEmptyCells() {
        gameEngine.removeItems(listOf(95, 96, 97))
        print()
        gameEngine.fillEmptyCells()
        print()
    }

    private fun print() =
        printGame(gameEngine.listItems())
}