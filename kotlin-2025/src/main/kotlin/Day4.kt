package zeljko.com

import java.io.File

fun main() {
    var lines = File("src/main/resources/input4.txt").readLines().toMutableList()
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

    val directions = Direction.entries

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

enum class Direction(val dy: Int, val dx: Int) {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    UP_LEFT(-1, -1),
    UP_RIGHT(-1, 1),
    DOWN_LEFT(1, -1),
    DOWN_RIGHT(1, 1)
}