package zeljko.com

import java.io.File

fun main() {

    val ingredients = File("src/main/resources/input5.txt").readLines()
    val freshIngredientsRanges = mutableListOf<LongRange>()
    val availableIngredients = mutableListOf<Long>()

    for (ingredient in ingredients) {

        if (ingredient.isEmpty()) {
            continue
        }

        if (ingredient.contains("-")) {
            val parts = ingredient.split("-", limit = 2)
            freshIngredientsRanges.add(parts[0].toLong()..parts[1].toLong())
        } else {
            availableIngredients.add(ingredient.toLong())
        }
    }

    // PART 1
    val count = availableIngredients.count { ingredient ->
        freshIngredientsRanges.any { range -> ingredient in range }
    }

    println(count)

    // PART 2
    val sortedFreshIngredientsRanges =
        freshIngredientsRanges.sortedWith(compareBy({ it.first }, { it.last })).toMutableList()
    println(sortedFreshIngredientsRanges)

    for (i in 0 until sortedFreshIngredientsRanges.size) {
        if (sortedFreshIngredientsRanges[i].isEmpty()) continue
        for (j in i + 1 until sortedFreshIngredientsRanges.size) {
            if (sortedFreshIngredientsRanges[j].isEmpty()) continue
            if (sortedFreshIngredientsRanges[i].last() >= sortedFreshIngredientsRanges[j].first()) {
                sortedFreshIngredientsRanges[i] = sortedFreshIngredientsRanges[i].first..maxOf(
                    sortedFreshIngredientsRanges[i].last,
                    sortedFreshIngredientsRanges[j].last
                )
                sortedFreshIngredientsRanges[j] = LongRange.EMPTY
            }

        }
    }

    val mergedFreshIngredientsRange = sortedFreshIngredientsRanges.filter { it != LongRange.EMPTY }
    println(mergedFreshIngredientsRange)
    var count2 : Long = 0L

    for (freshIngredient in mergedFreshIngredientsRange){
        count2 += (freshIngredient.last - freshIngredient.first  + 1L)
    }

    println(count2)
}