package zeljko.com

import math.Direction
import math.Vec2
import math.plus
import readInputLines


fun main() {
    val lines = readInputLines(false, 2024, 6)
    guardGallivant(lines)
    guardGallivant2(lines)
}

fun guardGallivant2(lines: List<String>) {

}

private fun guardGallivant(lines: List<String>) {
    val obstructions = mutableSetOf<Vec2>()
    var guard = Vec2(0, 0)

    val height = lines.size
    val width = lines[0].length

    lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, char ->
            if (char == '#') {
                obstructions.add(Vec2(x, y))
            }
            if (char == '^') {
                guard = Vec2(x, y)
            }
        }
    }

    var dir = Direction.UP
    val steps = mutableSetOf(guard)

    while (true) {
        val next = guard + dir

        if (next.x !in 0 until width || next.y !in 0 until height) {
            break
        }

        if (next in obstructions) {
            dir = dir.turnRight()
        } else {
            guard = next
            steps.add(guard)
        }
    }

    println(steps.size)
}

