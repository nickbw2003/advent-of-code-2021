import kotlin.math.abs

fun main() {
    fun mapInput(input: List<String>): List<Int> {
        return input[0]
            .split(",")
            .mapNotNull { rawValue ->
                rawValue.toIntOrNull()
            }
    }

    fun calculateLowestPossibleCosts(
        input: List<Int>,
        calculateStepCosts: (value: Int, horizontalPosition: Int) -> Int
    ): Int {
        val max = input.maxByOrNull { value -> value }
        val min = input.minByOrNull { value -> value }
        if (max == null || min == null) {
            return 0
        }
        var lowestPossibleResult = -1
        var totalCost = 0
        (max downTo min).forEach { horizontalPosition ->
            input.forEach { value ->
                totalCost += calculateStepCosts(value, horizontalPosition)
            }
            if (lowestPossibleResult == -1 || lowestPossibleResult > totalCost) {
                lowestPossibleResult = totalCost
            }
            totalCost = 0
        }
        return lowestPossibleResult
    }

    fun part1(input: List<String>): Int {
        val inputAsInt = mapInput(input)
        return calculateLowestPossibleCosts(inputAsInt) { value, horizontalPosition ->
            abs(value - horizontalPosition)
        }
    }

    fun part2(input: List<String>): Int {
        val inputAsInt = mapInput(input)
        return calculateLowestPossibleCosts(inputAsInt) { value, horizontalPosition ->
            val stepsNeeded = abs(value - horizontalPosition)
            (0 until stepsNeeded).mapIndexed { index, _ ->
                index + 1
            }.sumOf { it }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
