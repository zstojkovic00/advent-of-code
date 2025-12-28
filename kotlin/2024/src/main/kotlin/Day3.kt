package zeljko.com

import readInputLines

fun main() {
    val lines = readInputLines(false, 2024, 3)
    partOne(lines)
    partTwo(lines)
}

private fun partOne(lines: List<String>) {
    val pattern = Regex("mul\\(\\d+,\\d+\\)")
    val muls = pattern.findAll(lines.toString())
    var result = 0;
    muls.forEach { mul ->
        val values = mul.value.substringAfter("mul(").substringBefore(")")
        val (a, b) = values.split(",").map { it.toInt() }
        result += a * b
    }

    println(result)
}

private fun partTwo(lines: List<String>) {
    val pattern = Regex("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)")
    val muls = pattern.findAll(lines.toString())
    var result = 0
    var enabled = true

    muls.forEach { mul ->
        println(mul.value)
        when (mul.value) {
            "do()" -> enabled = true
            "don't()" -> enabled = false
            else -> if (enabled) {
                val values = mul.value.substringAfter("mul(").substringBefore(")")
                val (a, b) = values.split(",").map { it.toInt() }
                result += a * b
            }
        }
    }
    println(result)
}

