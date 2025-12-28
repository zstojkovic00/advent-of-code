package zeljko.com

import math.Direction
import math.Vec2
import math.plus
import readInputString


fun isInsideGrid(pos: Vec2, grid: List<String>): Boolean {
    return pos.y in grid.indices && pos.x in grid[pos.y].indices
}

fun main() {
    val input = readInputString(false, 2025, 7)
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
    val visited = mutableSetOf<Pair<Vec2, Direction>>()

    fun followBeam(start: Vec2, direction: Direction) {
        var pos = start

        while (isInsideGrid(pos, grid)) {
            if (Pair(pos, direction) in visited) return
            visited.add(Pair(pos, direction))

            if (grid[pos.y][pos.x] == '^') {
                count++
                followBeam(pos + Direction.DOWN_LEFT, Direction.DOWN)
                followBeam(pos + Direction.RIGHT, Direction.DOWN)
                return
            }
            pos += direction
        }
    }

    followBeam(manifoldLocation, Direction.DOWN)
    println("count: $count")
}

