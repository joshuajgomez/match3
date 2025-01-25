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

        check3Match().let {
            if (it.isNotEmpty()) {
                // match found
                removeItems(it)
            }
        }
    }

    private fun removeItems(list: List<Int>) {
        println("Removing $list")
    }

    private fun check3Match(): List<Int> {
        var match = listOf<Int>()
        items.forEachRow { it ->
            check3MatchPerList(it).let {
                if (it.isNotEmpty()) {
                    match = it
                    return@forEachRow
                }
            }
        }
        if (match.isNotEmpty()) return match
        items.forEachCol { it ->
            check3MatchPerList(it).let {
                if (it.isNotEmpty()) {
                    match = it
                    return@forEachCol
                }
            }
        }
        return match
    }

    private fun ArrayList<Item>.forEachRow(row: (ArrayList<Item>) -> Unit) {
        repeat(10) {
            val start = it * 10
            val endRange = (it * 10) + 10
            row(subList(start, endRange).toList() as ArrayList<Item>)
        }
    }

    private fun ArrayList<Item>.forEachCol(column: (ArrayList<Item>) -> Unit) {
        repeat(10) { i ->
            val col = ArrayList<Item>()
            repeat(10) {
                col.add(this[it * 10 + i])
            }
            column(col)
        }
    }

    private fun check3MatchPerList(list: List<Item>): List<Int> {
        println("Checking ${list.map { it.letter }}")
        val matches = ArrayList<Int>()
        var letter = ""
        list.forEachIndexed { i, it ->
            when {
                matches.size == 5 -> return@forEachIndexed
                letter == it.letter -> matches.add(it.position)
                matches.size > 2 -> return@forEachIndexed
                else -> matches.clear()
            }
            letter = it.letter
            matches.addUnique(it.position)
        }
        return when {
            matches.size > 2 -> matches
            else -> emptyList()
        }
    }

    fun listItems(): List<Item> {
        return items
    }

    private fun <Int> ArrayList<Int>.addUnique(i: Int) {
        when {
            !contains(i) -> add(i)
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
}

