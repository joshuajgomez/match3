package com.joshgm3z.match3.utils

import com.joshgm3z.match3.model.candy.Blue
import com.joshgm3z.match3.model.candy.Candy
import com.joshgm3z.match3.model.candy.Green
import com.joshgm3z.match3.model.candy.Purple
import com.joshgm3z.match3.model.candy.Red
import com.joshgm3z.match3.model.candy.Yellow

fun randomCandy() = listOf(Red(), Green(), Blue(), Yellow(), Purple()).random()

fun getCandies(): List<Candy> = listOf(
    Red(), Green(), Blue(), Yellow(), Purple(), Red(), Green(), Blue(), Yellow(), Purple(),
    Red(), Green(), Blue(), Yellow(), Purple(), Red(), Green(), Blue(), Yellow(), Purple(),
    Red(), Green(), Blue(), Yellow(), Purple(), Red(), Green(), Blue(), Yellow(), Purple(),
    Red(), Green(), Blue(), Yellow(), Purple(), Red(), Green(), Blue(), Yellow(), Purple(),
    Red(), Green(), Blue(), Yellow(), Purple(), Red(), Green(), Blue(), Yellow(), Purple(),
    Red(), Green(), Blue(), Yellow(), Purple(), Red(), Green(), Blue(), Yellow(), Purple(),
    Red(), Green(), Blue(), Yellow(), Purple(), Red(), Green(), Blue(), Yellow(), Purple(),
    Red(), Green(), Blue(), Yellow(), Purple(), Red(), Green(), Blue(), Yellow(), Purple(),
    Red(), Green(), Blue(), Yellow(), Purple(), Red(), Green(), Blue(), Yellow(), Purple(),
    Red(), Green(), Blue(), Yellow(), Purple(), Red(), Green(), Blue(), Yellow(), Purple(),
).shuffled()
