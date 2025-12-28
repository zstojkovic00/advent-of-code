import java.io.File

fun readInputString(test: Boolean, year: Int, day: Int): String {
    val folder = if (test) "test" else "input"
    val root = File("").absolutePath.let {
        if (it.endsWith(year.toString())) File(it).parent else it
    }
    return File("$root/$year/src/main/resources/$folder/$day.txt").readText()
}

fun readInputLines(test: Boolean, year: Int, day: Int): List<String> {
    val folder = if (test) "test" else "input"
    val root = File("").absolutePath.let {
        if (it.endsWith(year.toString())) File(it).parent else it
    }
    return File("$root/$year/src/main/resources/$folder/$day.txt").readLines()
}