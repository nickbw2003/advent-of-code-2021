fun main() {
    fun part1(input: List<String>): Int {
        val inputAsInt = input.mapNotNull { str ->
            str.toIntOrNull()
        }
        return inputAsInt
            .filterIndexed { index, currentValue ->
                if (index == 0) {
                    // skip
                    return@filterIndexed false
                }
                val previousValue = inputAsInt[index - 1]
                currentValue > previousValue
            }
            .count()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
