package zeljko.com

import readInputLines


fun main() {
    val lines = readInputLines(false, 2025, 12).drop(30)
    var possible = 0

    for (line in lines) {
        if (line.isBlank()) continue

        val parts = line.split(": ")
        val dimensions = parts[0].split("x")
        val length = dimensions[0].toInt()
        val width = dimensions[1].toInt()

        val nums = parts[1].trim().split(" ").map { it.toInt() }

        var naiveUpper = 0
        for (i in 1 until nums.size) {
            naiveUpper += nums[i]
        }

        if (naiveUpper <= length / 3 * width / 3) {
            possible++
        }
    }

    println(possible)
}