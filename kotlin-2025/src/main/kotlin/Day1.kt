package zeljko.com

import java.io.File
import kotlin.math.abs


fun main(args: Array<String>) {
    val lines = File("src/main/resources/input1.txt").readLines()

   val res1 = partOne(lines)
   println("Result part 1: $res1")

    val res2 = partTwo(lines)
    println("Result part 2: $res2")
}

fun partOne(lines: List<String>): Int {
    var count = 0
    var start = 50

    lines.forEach { line ->
        val dir = line.substring(0, 1)
        val num = Integer.valueOf(line.substring(1))

        var rawRes: Int

        if (dir == "L") {
            rawRes = start - num
        } else {
            rawRes = start + num
        }

        start = rawRes.mod(100)

        if (start == 0) {
            count++
        }
    }

    return count
}

fun partTwo(lines: List<String>): Int {
    var count = 0
    var start = 50

    lines.forEach { line ->
        val dir = line.take(1)
        val num = Integer.valueOf(line.substring(1))

        val step = if (dir == "L") -1 else 1

        repeat(num) {
            start += step
            val res = start.mod(100)

            if (res == 0) {
                count++
            }
        }
    }

    return count
}