package zeljko.com

import java.io.File


fun main() {
    val lines = File("src/main/resources/input6.txt").readLines()
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
}

data class Problem(val numbers: MutableList<Long>, val operator: String?)

