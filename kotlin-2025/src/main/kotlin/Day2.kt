package zeljko.com

import java.io.File

fun main() {
    val file = File("src/main/resources/input2.txt").readText()
    val ids: List<String> = file.split(",")
    val res1 = getInvalidIdsSum1(ids)
    println("Result: $res1")
    val res2 = getInvalidIdsSum2(ids)
    println("Result: $res2")
}


fun getInvalidIdsSum2(ids: List<String>): Long {
    var res: Long = 0
    for (id in ids) {
        val numbers = id.trim().split("-")
        val numStart = numbers[0].toLong()
        val numEnd = numbers[1].toLong()

        for (i in numStart..numEnd) {
            if (isInvalidID2(i)) {
                res += i
            }
        }
    }
    return res
}

fun getInvalidIdsSum1(ids: List<String>): Long {
    var res: Long = 0
    for (id in ids) {
        val numbers = id.trim().split("-")
        val numStart = numbers[0].toLong()
        val numEnd = numbers[1].toLong()

        for (i in numStart..numEnd) {
            if (isInvalidID(i)) {
                res += i
            }
        }
    }
    return res
}

fun isInvalidID(num: Long): Boolean {
    val numString = num.toString()
    val numHalf = numString.take(numString.length / 2)
    val numRest = numString.substring(numString.length / 2)
    return numHalf == numRest
}

fun isInvalidID2(num: Long): Boolean {
    val numString = num.toString();

    for (pLength in 1..numString.length / 2) {
        if (numString.length % pLength == 0) {
            val p = numString.take(pLength)
            val times = numString.length / pLength
            val repeat = p.repeat(times)

            if (repeat == numString) {
                return true
            }
        }
    }
    return false
}