package zeljko.com

import java.io.File

data class Vec2(val x: Int, val y: Int)

enum class Direction2(val x: Int, val y: Int) {
    Up(0, -1),
    Down(0, 1),
    Left(-1, 0),
    Right(1, 0)
}

operator fun Vec2.plus(direction: Direction2) = Vec2(x + direction.x, y + direction.y)

fun isInsideGrid(pos: Vec2, grid: List<String>): Boolean {
    return pos.y in grid.indices && pos.x in grid[pos.y].indices
}

fun main() {
    val input = File("src/main/resources/input7.txt").readText()
    val grid = input.lines()

    var manifoldLocation = Vec2(0, 0)

    grid.forEachIndexed { y, line ->
        line.forEachIndexed { x, char ->
            if (char == 'S') {
                manifoldLocation = Vec2(x, y)
            }
        }
    }

    var count = 0
    val visited = mutableSetOf<Pair<Vec2, Direction2>>()

    fun followBeam(start: Vec2, direction: Direction2) {
        var pos = start

        while (isInsideGrid(pos, grid)) {
            if (Pair(pos, direction) in visited) return
            visited.add(Pair(pos, direction))

            if (grid[pos.y][pos.x] == '^') {
                count++
                followBeam(pos + Direction2.Left, Direction2.Down)
                followBeam(pos + Direction2.Right, Direction2.Down)
                return
            }
            pos += direction
        }
    }

    followBeam(manifoldLocation, Direction2.Down)
    println("count: $count")
}

