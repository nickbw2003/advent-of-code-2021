fun main() {
    fun mapInput(input: List<String>): List<Int> {
        return input[0]
            .split(",")
            .mapNotNull { str ->
                str.toIntOrNull()
            }
    }

    fun fishSimulation(input: List<Int>, simulationTime: Int): Long {
        val fishContainer = LongArray(9).apply {
            input.forEach { inputTimer ->
                this[inputTimer] = this[inputTimer] + 1
            }
        }
        (0 until simulationTime).forEach { _ ->
            val amountOfFishToAdd = fishContainer[0]
            for (i in 1 until fishContainer.size) {
                fishContainer[i - 1] = fishContainer[i]
            }
            fishContainer[fishContainer.size - 1] = 0
            fishContainer[8] += amountOfFishToAdd
            fishContainer[6] += amountOfFishToAdd
        }
        return fishContainer.sum()
    }

    fun part1(input: List<String>): Long {
        return fishSimulation(
            mapInput(input),
            80
        )
    }

    fun part2(input: List<String>): Long {
        return fishSimulation(
            mapInput(input),
            256
        )
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934L)
    check(part2(testInput) == 26984457539L)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
