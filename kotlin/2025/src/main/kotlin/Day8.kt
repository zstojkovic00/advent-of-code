package zeljko.com

import math.Vec3D
import readInputLines
import kotlin.math.pow
import kotlin.math.sqrt

data class BoxPair(val box1: Vec3D, val box2: Vec3D, val distance: Double)

fun main() {
    val lines = readInputLines(false, 2025, 8)
    val boxes = mutableListOf<Vec3D>()

    for (line in lines) {
        val numbers = line.split(",", limit = 3)
        boxes.add(
            Vec3D(
                numbers[0].toDouble(),
                numbers[1].toDouble(),
                numbers[2].toDouble()
            )
        )
    }
    val pairs = mutableListOf<BoxPair>()
    val boxGroups = mutableMapOf<Vec3D, Int>()

    for (i in 0 until boxes.size) {
        boxGroups[boxes[i]] = i
        for (j in i + 1 until boxes.size) {
            val distance = euclideanDistance(boxes[i], boxes[j])
            pairs.add(BoxPair(boxes[i], boxes[j], distance))
        }

    }
    pairs.sortBy { it.distance }

    for (i in 0 until 1000) {
        val pair = pairs[i]
        val box1Junction = boxGroups[pair.box1]
        val box2Junction = boxGroups[pair.box2]

        if (box1Junction != box2Junction) {
            for (box in boxGroups.keys) {
                if (boxGroups[box] == box2Junction) {
                    boxGroups[box] = box1Junction as Int
                }
            }
        }
    }

    val junctionGroups = mutableMapOf<Int, Int>()

    for (junction in boxGroups.values) {
        junctionGroups[junction] = junctionGroups.getOrDefault(junction, 0) + 1
    }

    val max3 = junctionGroups.values.sorted().reversed()
    val count = max3[0] * max3[1] * max3[2]
    println(count)

}

// Formula za euclidean distance za tri dimenzije je: d(p,q) = SQRT( (p1-q1)^2 + (p2 - q2)^2 + (p3 - q3)^2
fun euclideanDistance(p1: Vec3D, p2: Vec3D): Double {
    return sqrt(
        (p1.x - p2.x).pow(2) +
                (p1.y - p2.y).pow(2) +
                (p1.z - p2.z).pow(2)

    )
}
