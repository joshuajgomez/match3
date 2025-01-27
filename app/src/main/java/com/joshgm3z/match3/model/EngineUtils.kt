package com.joshgm3z.match3.model

import com.joshgm3z.match3.model.candy.Candy

fun <Int> ArrayList<Int>.addUnique(i: Int) {
    when {
        !contains(i) -> add(i)
    }
}

fun List<Candy>.swap(
    source: Int,
    target: Int
): List<Candy> {
    val sourceItem = this[source]
    val targetItem = this[target]
    return map {
        when (it.position) {
            source -> targetItem
            target -> sourceItem
            else -> it
        }
    }
}

inline fun List<Candy>.forEachRow(row: (List<Candy>) -> Unit) {
    repeat(10) {
        val start = it * 10
        val endRange = (it * 10) + 10
        row(subList(start, endRange))
    }
}

inline fun List<Candy>.forEachCol(column: (List<Candy>) -> Unit) {
    repeat(10) { i ->
        val col = arrayListOf<Candy>()
        repeat(10) {
            this.elementAt(it * 10 + i).let { it1 -> col.add(it1) }
        }
        column(col)
    }
}

fun check3MatchPerList(list: List<Candy>): List<Int> {
    val matches = ArrayList<Int>()
    var letter = ""
    run loop@{
        list.forEach {
            when {
                matches.size == 5 -> return@loop
                letter == it.letter -> matches.add(it.position)
                matches.size >= 3 -> return@loop
                else -> matches.clear()
            }
            letter = it.letter
            it.let {
                matches.addUnique(it.position)
            }
        }
    }
    return when {
        matches.size >= 3 -> matches
        else -> emptyList()
    }
}

fun printCandies(candies: List<Candy>) {
    candies.forEachIndexed { index, candy ->
        print("$candy\t")
        if ((index + 1) % 10 == 0) println()
    }
    println()
}

fun List<Candy>.reposition()
        : List<Candy> = mapIndexed { index, item ->
    item.position = index
    item
}