import java.lang.Integer.max

fun main() {
    fun String.extractCommand(): Command? {
        val commandType = CommandType.values()
            .map { commandType -> commandType.toString() }
            .firstOrNull { rawCommandType ->
                contains(rawCommandType)
            }
            ?.let { rawCommandType ->
                CommandType.valueOf(rawCommandType)
            }

        val length = replace(commandType.toString(), "").trim().toIntOrNull()

        return if (commandType != null && length != null) {
            Command(
                type = commandType,
                length = length
            )
        } else {
            null
        }
    }

    fun List<Command>.play(): RouteDestination {
        var sumHor = 0
        var sumDepth = 0
        forEach { command ->
            when (command.type) {
                CommandType.forward -> {
                    sumHor += command.length
                }
                CommandType.down -> {
                    sumDepth += command.length
                }
                CommandType.up -> {
                    sumDepth -= command.length
                    sumDepth = max(sumDepth, 0)
                }
            }
        }
        return RouteDestination(
            horizontal = sumHor,
            depth = sumDepth
        )
    }

    fun part1(input: List<String>): Int = input
        .mapNotNull { rawCommand ->
            rawCommand.extractCommand()
        }
        .play()
        .let { routeDestination -> routeDestination.depth * routeDestination.horizontal }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

enum class CommandType {
    forward,
    down,
    up
}

data class Command(
    val type: CommandType,
    val length: Int
)

data class RouteDestination(
    val horizontal: Int = 0,
    val depth: Int = 0
)
