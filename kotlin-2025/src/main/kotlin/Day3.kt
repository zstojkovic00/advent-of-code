package zeljko.com

import java.io.File

fun main() {
    val lines = File("src/main/resources/test3.txt").readLines()
    val banks = lines.map { line ->
        line.map { it.digitToInt() }
    }

    val res1: Int = getJoltage1(banks)
    print("Result: $res1")

    val res2: Long = getJoltage2(banks)
    print("Result: $res2")
}


// prvi broj je uvek najveci u nizu i on mora da bude u opsegu [0, batteries.size - 11] jer bar 11 broja mora ostati
// 9XXXXX je uvek veci od 8XXXXXX etc..
// nakon toga trazi se drugi broj od [1-digitPos, batteries.size - 10] etc..
fun getJoltage2(banks: List<List<Int>>): Long {
    var res = 0L

    for (batteries in banks) {
        println(batteries)

        for ((index, battery) in batteries.withIndex()) {
        }

    }

    return res
}

fun getJoltage1(banks: List<List<Int>>): Int {
    var res = 0
    for (batteries in banks) {
        println(batteries)
        var max = 0;
        for ((index, battery) in batteries.withIndex()) {
            if (batteries.size == index + 1) break
            val batteriesSub = batteries.subList(index + 1, batteries.size)
            val subMax = batteriesSub.max()
            val curMax = (battery * 10) + subMax;
            if (curMax > max) {
                max = curMax
            }
        }
        res += max
    }
    return res
}
