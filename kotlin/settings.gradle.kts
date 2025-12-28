plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "advent-of-code"

include("aoc-2024")
project(":aoc-2024").projectDir = file("2024")

include("aoc-2025")
project(":aoc-2025").projectDir = file("2025")

include("utils")