package zeljko.com

import java.io.File

fun main() {
    val lines = File("src/main/resources/input4.txt").readLines()
    val accessibleCount = findAccessibleRollsOfPaper(lines)
    print(accessibleCount)

}

/* Zadatak je da se prodje kroz svaki element i da se proveri da li ima 4 susedne rolne papira @
 Zato sto forklift moze da pristupi samo tim papirima.*/
fun findAccessibleRollsOfPaper(lines: List<String>): Int {
    var count = 0

    for (y in lines.indices) {
        for (x in lines[y].indices) {
            val cell = lines[y][x]

            if (cell == '@') {
                val neighborCount = countNeighbors(y, x, lines)

                if (neighborCount < 4) {
                    count++
                }
            }
        }
    }
    return count
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