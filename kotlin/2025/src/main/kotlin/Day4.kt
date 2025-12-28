package zeljko.com

import math.Direction
import readInputLines

fun main() {
    var lines = readInputLines(false, 2025, 4).toMutableList()
//    val accessibleCount = findAccessibleRollsOfPaper(lines)
//    print(accessibleCount)

    var accessibleRolls = findAccessibleRollsOfPaper2(lines)
    var count = accessibleRolls.size

    while (accessibleRolls.isNotEmpty()) {
        lines = lines.mapIndexed { y, line ->
            line.mapIndexed { x, char ->
                if (accessibleRolls.contains(Pair(y, x))) '.' else char
            }.joinToString("")
        }.toMutableList()

        accessibleRolls = findAccessibleRollsOfPaper2(lines)
        count += accessibleRolls.size
    }

    println(count)
}

/* Zadatak je da se prodje kroz svaki element i da se proveri da li ima manje od 4 susedne rolne papira @
 Zato sto forklift moze da pristupi samo tim papirima.*/
fun findAccessibleRollsOfPaper(lines: List<String>): Int {
    var count = 0
    var accessibleRollsPos = mutableListOf<Pair<Int, Int>>()

    for (y in lines.indices) {
        for (x in lines[y].indices) {
            val cell = lines[y][x]

            if (cell == '@') {
                val neighborCount = countNeighbors(y, x, lines)

                if (neighborCount < 4) {
                    accessibleRollsPos.add(Pair(y, x))
                    count++
                }
            }
        }
    }

    println(accessibleRollsPos)
    return count
}

/*
    Part2: Zadatak je da nakon sto smo pronasli rolne papira koja imaju manje od 4 suseda, treba da ih obrisemo iz grid-a i da to radimo
    dokle god mozemo, count je zbir svih obrisanih
 */
fun findAccessibleRollsOfPaper2(lines: List<String>): List<Pair<Int, Int>> {
    var count = 0
    var accessibleRollsPos = mutableListOf<Pair<Int, Int>>()

    for (y in lines.indices) {
        for (x in lines[y].indices) {
            val cell = lines[y][x]

            if (cell == '@') {
                val neighborCount = countNeighbors(y, x, lines)

                if (neighborCount < 4) {
                    accessibleRollsPos.add(Pair(y, x))
                    count++
                }
            }
        }
    }

    return accessibleRollsPos
}

fun countNeighbors(y: Int, x: Int, lines: List<String>): Int {
    var count = 0

    val directions = Direction.cardinal

    for (dir in directions) {
        val newY = y + dir.dy
        val newX = x + dir.dx

        if (newY in lines.indices && newX in lines[newY].indices) {
            if (lines[newY][newX] == '@') {
                count++
            }
        }
    }

    return count
}