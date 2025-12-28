package zeljko.com

import readInputLines

fun main() {
    val lines = readInputLines(false, 2025, 10)
    var totalButtonPresses = 0

    for (line in lines) {
        val parts = line.split(" ")
        val targetLight = mutableListOf<Int>()
        val buttons = mutableListOf<List<Int>>()

        for (part in parts) {
            if (part.startsWith("[")) {
                val lights = part.substring(1, part.length - 1)
                    .split("")
                    .filter { it.isNotEmpty() }
                    .map { if (it == "#") 1 else 0 }
                targetLight.addAll(lights)
            } else if (part.startsWith("(")) {
                val buttonLights = part.substring(1, part.length - 1)
                    .split(",")
                    .map { it.toInt() }
                buttons.add(buttonLights)
            } else if (part.startsWith("{")) {
                part.substring(1, part.length - 1)
                    .split(",")
                    .map { it.toInt() }.toMutableList()
            }
        }

        val lightsToTurnOn = targetLight.indices.filter { targetLight[it] == 1 }.toSet()
        var minButtonPresses = Int.MAX_VALUE

        for (combination in 0 until (1 shl buttons.size)) {
            val currentLightsOn = mutableSetOf<Int>()
            var pressed = 0

            for (i in buttons.indices) {
                if ((combination and (1 shl i)) != 0) {
                    pressed++
                    for (lightIndex in buttons[i]) {
                        if (currentLightsOn.contains(lightIndex)) {
                            currentLightsOn.remove(lightIndex)
                        } else {
                            currentLightsOn.add(lightIndex)
                        }
                    }
                }
            }

            if (currentLightsOn == lightsToTurnOn) {
                if (pressed < minButtonPresses) {
                    minButtonPresses = pressed
                }
            }
        }

        totalButtonPresses += minButtonPresses
    }

    println(totalButtonPresses)
}