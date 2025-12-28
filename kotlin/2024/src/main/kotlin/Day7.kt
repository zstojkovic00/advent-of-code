package zeljko.com

import readInputLines


fun main() {
    val lines = readInputLines(false, 2024, 7)
    println(bridgeRepair(lines))
}

fun bridgeRepair(lines: List<String>): Long {
    var result: Long = 0
    lines.forEach { line ->
        val numbers = line.substringAfter(": ").split(" ").map { it.toLong() }.toList()
        val value = line.substringBefore(":").toLong()

        if (isValueValid(numbers, value)) {
            result += value
        }
    }

    return result
}

private fun isValueValid(numbers: List<Long>, value: Long, i: Int = 1, current: Long = numbers[0]): Boolean {
    if (i == numbers.size) {
        return current == value
    }

    if (isValueValid(numbers, value, i + 1, current + numbers[i])) {
        return true
    }
    if (isValueValid(numbers, value, i + 1, current.concatenation(numbers[i]))) {
        return true
    }

    return isValueValid(numbers, value, i + 1, current * numbers[i])
}

private fun Long.concatenation(l: Long): Long {
    return "$this$l".toLong()
}
