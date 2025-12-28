package math

data class Vec2(val x: Int, val y: Int)
data class Vec2L(val x: Long, val y: Long)
data class Vec2D(val x: Double, val y: Double)

operator fun Vec2.plus(direction: Direction) = Vec2(x + direction.dx, y + direction.dy)
