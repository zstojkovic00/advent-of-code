import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Each line represents a single equation. The test value appears before the colon on each line;
 * It is your job to determine whether the remaining numbers can be combined with operators to produce the test value
 */

class Day7Test {
    companion object {
        val lines = readInputLines(test = true, 2024, 7)
    }

    @Test
    fun `should find equations that match their test values`() {
        var result = 0

        lines.forEach { line ->
            val numbers = line.substringAfter(": ").split(" ").map { it.toInt() }.toList()
            val value = line.substringBefore(":").toInt()

            if (isValueValid(numbers, value)) {
                result += value
            }
        }

        assertThat(result).isEqualTo(3749)
    }

    private fun isValueValid(numbers: List<Int>, value: Int, i: Int = 1, current: Int = numbers[0]): Boolean {
        if (i == numbers.size) {
            return current == value
        }

        if (isValueValid(numbers, value, i + 1, current + numbers[i])) {
            return true
        }

        return isValueValid(numbers, value, i + 1, current * numbers[i])
    }


}