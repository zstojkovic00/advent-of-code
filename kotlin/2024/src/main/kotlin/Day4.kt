package zeljko.com

import readInputLines


fun main() {
    val lines = readInputLines(false, 2024, 4)
    val times = partOne(lines)
    println(times)
    val times2 = partTwo(lines)
    println(times2)

}

private fun partOne(lines: List<String>): Int {
    var times = 0
    for (i in lines.indices) {
        for (j in lines[i].indices) {
            when {

                lines[i][j] == 'X' -> {

                    // horizontal right
                    if (j + 3 < lines[i].length) {
                        if (lines[i][j + 1] == 'M' &&
                            lines[i][j + 2] == 'A' &&
                            lines[i][j + 3] == 'S'
                        )
                            times++
                    }

                    // horizontal left
                    if (j >= 3) {
                        if (lines[i][j - 1] == 'M' &&
                            lines[i][j - 2] == 'A' &&
                            lines[i][j - 3] == 'S'
                        )
                            times++
                    }

                    // vertical up
                    if (i >= 3) {
                        if (lines[i - 1][j] == 'M' &&
                            lines[i - 2][j] == 'A' &&
                            lines[i - 3][j] == 'S'
                        ) {
                            times++
                        }
                    }

                    // vertical down
                    if (i + 3 < lines.size) {
                        if (lines[i + 1][j] == 'M' &&
                            lines[i + 2][j] == 'A' &&
                            lines[i + 3][j] == 'S'
                        ) {
                            times++
                        }
                    }

                    // diagonal right down
                    if (i + 3 < lines.size && j + 3 < lines[i].length) {
                        if (lines[i + 1][j + 1] == 'M' &&
                            lines[i + 2][j + 2] == 'A' &&
                            lines[i + 3][j + 3] == 'S'
                        )
                            times++
                    }

                    // diagonal right up
                    if (i >= 3 && j >= 3) {
                        if (lines[i - 1][j - 1] == 'M' &&
                            lines[i - 2][j - 2] == 'A' &&
                            lines[i - 3][j - 3] == 'S'
                        )
                            times++
                    }

                    // diagonal left down
                    if (i + 3 < lines.size && j >= 3) {
                        if (lines[i + 1][j - 1] == 'M' &&
                            lines[i + 2][j - 2] == 'A' &&
                            lines[i + 3][j - 3] == 'S'
                        )
                            times++
                    }


                    // diagonal left up
                    if (j + 3 < lines[i].length && i >= 3) {
                        if (lines[i - 1][j + 1] == 'M' &&
                            lines[i - 2][j + 2] == 'A' &&
                            lines[i - 3][j + 3] == 'S'

                        )
                            times++
                    }
                }
            }

        }
    }
    return times
}

private fun partTwo(lines: List<String>): Int {
    var times = 0
    for (i in lines.indices) {
        for (j in lines[i].indices) {

            if (lines[i][j] == 'A') {
                if (i >= 1 && i + 1 < lines.size &&
                    j >= 1 && j + 1 < lines[i].length
                ) {

                    val tl = lines[i - 1][j - 1]
                    val tr = lines[i - 1][j + 1]
                    val bl = lines[i + 1][j - 1]
                    val br = lines[i + 1][j + 1]

                    val leftDiagonal = listOf(tl, br)
                    val rightDiagonal = listOf(tr, bl)

                    val hasExpectedLettersLeftDiagonal = leftDiagonal.count { it == 'M' } == 1 && leftDiagonal.count { it == 'S' } == 1
                    val hasExpectedLettersRightDiagonal = rightDiagonal.count { it == 'M' } == 1 && rightDiagonal.count { it == 'S' } == 1

                    if (hasExpectedLettersLeftDiagonal && hasExpectedLettersRightDiagonal) times++

                }
            }
        }
    }
    return times
}

