package com.joshgm3z.match3.model

import com.joshgm3z.match3.ui.Item

fun <Int> ArrayList<Int>.addUnique(i: Int) {
    when {
        !contains(i) -> add(i)
    }
}

fun <E> ArrayList<E>.swap(
    source: Int,
    target: Int
) {
    val sourceTemp = this[source]
    val targetTemp = this[target]
    this[source] = targetTemp
    this[target] = sourceTemp
}

inline fun ArrayList<Item?>.forEachRow(row: (ArrayList<Item>) -> Unit) {
    repeat(10) {
        val start = it * 10
        val endRange = (it * 10) + 10
        row(subList(start, endRange) as ArrayList<Item>)
    }
}

inline fun ArrayList<Item?>.forEachCol(column: (ArrayList<Item>) -> Unit) {
    repeat(10) { i ->
        val col = ArrayList<Item>()
        repeat(10) {
            this.elementAt(it * 10 + i)?.let { it1 -> col.add(it1) }
        }
        column(col)
    }
}

fun check3MatchPerList(list: List<Item>): List<Int> {
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
            matches.addUnique(it.position)
        }
    }
    return when {
        matches.size >= 3 -> matches
        else -> emptyList()
    }
}

fun <Item> List<Item?>.toArrayList(): ArrayList<Item?> = this as ArrayList<Item?>

fun printGame(items: List<Item?>) {
    items.forEachIndexed { index, item ->
        print("${item?.letter ?: "?"}  ")
        if ((index + 1) % 10 == 0) {
            println()
        }
    }
}