package zeljko.com

import java.io.File

fun main(){

    val ingredients = File("src/main/resources/input5.txt").readLines()
    val freshIngredientsRanges = mutableListOf<LongRange>()
    val availableIngredients = mutableListOf<Long>()

    for(ingredient in ingredients){

        if(ingredient.isEmpty()){
            continue
        }

        if(ingredient.contains("-")){
            val parts = ingredient.split("-", limit = 2)
            freshIngredientsRanges.add(parts[0].toLong()..parts[1].toLong())
        } else {
            availableIngredients.add(ingredient.toLong())
        }
    }

    val count = availableIngredients.count { ingredient ->
        freshIngredientsRanges.any { range -> ingredient in range }
    }
    println(count)
}