fun main() {
    fun countIncreases(input: List<Int>): Int {
        return input
            .filterIndexed { index, currentValue ->
                if (index == 0) {
                    // skip
                    return@filterIndexed false
                }
                val previousValue = input[index - 1]
                currentValue > previousValue
            }
            .count()
    }

    fun part1(input: List<String>): Int {
        val inputAsInt = input.mapNotNull { str ->
            str.toIntOrNull()
        }
        return countIncreases(inputAsInt)
    }

    fun part2(input: List<String>): Int {
        val inputAsInt = input.mapNotNull { str ->
            str.toIntOrNull()
        }
        val mappedInput = inputAsInt
            .mapIndexedNotNull { index, _ ->
                val highestStepIndex = index + 2
                if (highestStepIndex > (inputAsInt.size - 1)) {
                    null
                } else {
                    inputAsInt.slice(index..highestStepIndex)
                }
            }
            .map { listOfThree ->
                listOfThree.sum()
            }
        return countIncreases(mappedInput)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
