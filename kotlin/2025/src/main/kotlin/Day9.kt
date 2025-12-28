package zeljko.com

import math.Vec2L
import readInputLines
import kotlin.math.abs


fun main() {
    val lines = readInputLines(false, 2025, 9)
    val redTiles = mutableListOf<Vec2L>()

    for (line in lines) {
        val xy = line.split(",")
        redTiles.add(
            Vec2L(
                xy[0].toLong(),
                xy[1].toLong()
            )
        )
    }

    var maxRectangleArea = 0L
    for (i in 0 until redTiles.size) {
        for (j in i + 1 until redTiles.size) {
            if (redTiles[i].x != redTiles[j].x && redTiles[i].y != redTiles[j].y) {
                val rectangleArea = (abs(redTiles[i].x - redTiles[j].x) + 1L) * (abs(redTiles[i].y - redTiles[j].y) + 1L)
                if (maxRectangleArea < rectangleArea) {
                    maxRectangleArea = rectangleArea
                }
            }
        }
    }

    println(maxRectangleArea)
}