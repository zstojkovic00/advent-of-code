package zeljko.com

import readInputLines
import kotlin.math.abs


fun main() {
    val lines = readInputLines(false, 2024, 1);
    partOne(lines)
    partTwo(lines)
}

private fun partTwo(lines : List<String>) {
    var similarityScore = 0;
    val leftNumbers = mutableListOf<Int>()
    val rightNumbers = mutableListOf<Int>()

    lines.forEach { line ->
        val parts = line.split("   ")
        leftNumbers.add(parts[0].toInt())
        rightNumbers.add(parts[1].toInt())
    }

    for (i in leftNumbers) {
        var times = 0
        for (j in rightNumbers) {
            if (i == j) {
                times++
            }
        }
        similarityScore += (i * times)
    }

    println(similarityScore)
}

private fun partOne(lines : List<String>) {
    var distance = 0;
    val leftNumbers = mutableListOf<Int>()
    val rightNumbers = mutableListOf<Int>()

    lines.forEach { line ->
        val parts = line.split("   ")
        leftNumbers.add(parts[0].toInt())
        rightNumbers.add(parts[1].toInt())
    }
    leftNumbers.sort()
    rightNumbers.sort()

    leftNumbers.zip(rightNumbers).forEach { pair ->
        distance += abs(pair.first - pair.second)
    }

    println(distance)
}


