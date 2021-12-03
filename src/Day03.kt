fun main() {
    fun part1(input: List<String>): Int {
        val binaryLength = input.minOfOrNull { rawBinary ->
            rawBinary.length
        } ?: 0
        val binaries = (0 until binaryLength)
            .mapNotNull { index ->
                input
                    .groupingBy { rawBinary -> rawBinary[index] }
                    .eachCount()
                    .entries
                    .maxByOrNull { it.value }
                    ?.key
            }
        val gamma = binaries
            .joinToString("")
            .toInt(2)
        val invertedBinaries = binaries
            .map { char ->
                (char.code xor 1).toChar()
            }
        val epsilon = invertedBinaries
            .joinToString("")
            .toInt(2)
        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {
        val binaryLength = input.minOfOrNull { rawBinary ->
            rawBinary.length
        } ?: 0
        val filteredForO2 = input.toMutableList()
        val filteredForCo2 = input.toMutableList()
        val comparator: Comparator<Map.Entry<Char, Int>> = compareBy(
            { keyValue -> keyValue.value },
            { keyValue -> keyValue.key }
        )
        (0 until binaryLength).forEach { index ->
            val common = filteredForO2
                .groupingBy { rawBinary ->
                    rawBinary[index]
                }
                .eachCount()
                .entries
                .maxWithOrNull(comparator)
                ?.key
            val uncommon = filteredForCo2
                .groupingBy { rawBinary ->
                    rawBinary[index]
                }
                .eachCount()
                .entries
                .minWithOrNull(comparator)
                ?.key
            filteredForO2.retainAll { rawBinary ->
                rawBinary[index] == common
            }
            filteredForCo2.retainAll { rawBinary ->
                rawBinary[index] == uncommon
            }
        }
        val o2 = filteredForO2.firstOrNull()?.toInt(2) ?: 0
        val co2 = filteredForCo2.firstOrNull()?.toInt(2) ?: 0
        return o2 * co2
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
