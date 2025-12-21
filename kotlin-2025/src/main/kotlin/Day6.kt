package zeljko.com

import java.io.File


fun main() {
    val lines = File("src/main/resources/test6.txt").readLines()
    val problems = mutableMapOf<Int, Problem>()

    for ((i, line) in lines.withIndex()) {
        val elements = line.split(" ").filter { it.isNotEmpty() }
        val isLastLine = i == lines.size - 1

        for ((i, element) in elements.withIndex()) {

            if (!problems.containsKey(i)) {
                problems[i] = Problem(mutableListOf(), null)
            }

            val currentProblem = problems[i]!!

            if (isLastLine) {
                problems[i] = Problem(currentProblem.numbers, element)
            } else {
                currentProblem.numbers.add(element.toLong())
            }
        }
    }

    var count = 0L

    problems.forEach { problem ->
        var countPerProblem: Long = 0
        if (problem.value.operator == "*") {
            countPerProblem = problem.value.numbers.reduce { acc, num -> acc * num }
        } else if (problem.value.operator == "+") {
            countPerProblem = problem.value.numbers.sum()
        }
        count += countPerProblem
    }

    println(count)

    val problemsWithPadding = mutableListOf<Problem>()

    for (problem in problems.values.reversed()) {
        val stringNumbers = problem.numbers.map { it.toString() }
        val maxLengthPerNumbers: Int = stringNumbers.maxOf { it.length }

        val padded = stringNumbers.map { number ->
            if (number.length < maxLengthPerNumbers) {
                val padding = "0".repeat(maxLengthPerNumbers - number.length)
                number + padding
            } else {
                number
            }
        }

        val paddedAsLong = padded.map { it.toLong() }.toMutableList()
        problemsWithPadding.add(Problem(paddedAsLong, problem.operator))
    }

    var result = 0L

    for (problem in problemsWithPadding) {
        val rightToLeftNumbers = mutableMapOf<Int, MutableList<Char>>()
        println(problem.numbers)
        for (number in problem.numbers.map { it.toString() }) {
            for ((i, c) in number.reversed().withIndex()) {
                if (c != '0') {
                    rightToLeftNumbers.getOrPut(i) { mutableListOf() }.add(c)
                }
            }
        }

        println(rightToLeftNumbers)

        val rightToLeftNumbersLong = rightToLeftNumbers.values.map { charList ->
            charList.joinToString("").toLong()
        }

        println(rightToLeftNumbersLong)

        if (problem.operator == "*") {
            result = rightToLeftNumbersLong.reduce { acc, num -> acc * num }
        } else if (problem.operator == "+") {
            result = rightToLeftNumbersLong.sum()
        }

    }

    println(result)
}

data class Problem(val numbers: MutableList<Long>, val operator: String?)