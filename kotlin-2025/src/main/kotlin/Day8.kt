package zeljko.com

import java.io.File
import kotlin.math.pow
import kotlin.math.sqrt

data class Vec3(val x: Double, val y: Double, val z: Double)
data class BoxPair(val box1: Vec3, val box2: Vec3, val distance: Double)

fun main() {

    val lines = File("src/main/resources/input8.txt").readLines()
    println(lines)
    val boxes = mutableListOf<Vec3>()

    for (line in lines) {
        val numbers = line.split(",", limit = 3)
        boxes.add(
            Vec3(
                numbers[0].toDouble(),
                numbers[1].toDouble(),
                numbers[2].toDouble()
            )
        )
    }
    val pairs = mutableListOf<BoxPair>()

    for (i in 0 until boxes.size) {
        for (j in i + 1 until boxes.size) {
            val distance = euclideanDistance(boxes[i], boxes[j])
            pairs.add(BoxPair(boxes[i], boxes[j], distance))
        }

    }
    pairs.sortBy { it.distance }
    println(pairs)

    val junctions = mutableMapOf<Vec3, Int>()

    for ((index, box) in boxes.withIndex()) {
        junctions[box] = index
    }

    println(junctions)

    for (i in 0 until 1000) {
        val pair = pairs[i]

        val box1Junction = junctions[pair.box1]
        val box2Junction = junctions[pair.box2]

        if (box1Junction != box2Junction) {
            for (box in junctions.keys) {
                if (junctions[box] == box2Junction) {
                    junctions[box] = box1Junction as Int
                }
            }
        }
    }

    val junctionGroups = mutableMapOf<Int, Int>()

    for (junction in junctions.values) {
        junctionGroups[junction] = junctionGroups.getOrDefault(junction, 0) + 1
    }

    val max3 = junctionGroups.values.sorted().reversed()
    val count = max3[0] * max3[1] * max3[2]
    println(count)

}

// Formula za euclidean distance za tri dimenzije je: d(p,q) = SQRT( (p1-q1)^2 + (p2 - q2)^2 + (p3 - q3)^2
fun euclideanDistance(p1: Vec3, p2: Vec3): Double {
    return sqrt(
        (p1.x - p2.x).pow(2) +
                (p1.y - p2.y).pow(2) +
                (p1.z - p2.z).pow(2)

    )
}
