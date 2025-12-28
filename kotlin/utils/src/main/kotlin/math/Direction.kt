package math

enum class Direction(val dy: Int, val dx: Int) {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    UP_LEFT(-1, -1),
    UP_RIGHT(-1, 1),
    DOWN_LEFT(1, -1),
    DOWN_RIGHT(1, 1);

    fun turnRight(): Direction = when (this) {
        UP -> RIGHT
        RIGHT -> DOWN
        DOWN -> LEFT
        LEFT -> UP
        UP_RIGHT -> DOWN_RIGHT
        DOWN_RIGHT -> DOWN_LEFT
        DOWN_LEFT -> UP_LEFT
        UP_LEFT -> UP_RIGHT
    }

    companion object {
        val cardinal = listOf(UP, DOWN, LEFT, RIGHT)
        val diagonal = listOf(UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT)
    }
}