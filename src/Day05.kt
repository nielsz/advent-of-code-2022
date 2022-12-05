typealias StackLot = Array<ArrayDeque<Char>>

fun main() {
    fun part1(stackLot: StackLot, operations: List<List<Int>>): String {
        operations.onEach { operation ->
            repeat(operation[0]) {
                val crate = stackLot[operation[1] - 1].removeLast()
                stackLot[operation[2] - 1].addLast(crate)
            }
        }

        return stackLot
            .map { it.last() }
            .joinToString("")
    }

    fun part2(stackLot: StackLot, operations: List<List<Int>>): String {
        operations.onEach { operation ->
            val movableStack = ArrayDeque<Char>()
            repeat(operation[0]) {
                movableStack.add(stackLot[operation[1] - 1].removeLast())
            }
            repeat(operation[0]) {
                stackLot[operation[2] - 1].addLast(movableStack.removeLast())
            }
        }

        return stackLot
            .map { it.last() }
            .joinToString("")
    }

    val input = readInput("Day05")
    val (stack, stackCount, operations) = separateStackAndOperations(input)

    val op = setupOperations(operations)

    println(part1(setupStackLot(stack, stackCount), op)) // GFTNRBZPF
    println(part2(setupStackLot(stack, stackCount), op)) // VRQWPDSGP
}

fun setupStackLot(stack: List<String>, stackCount: Int): StackLot {
    val stack = stack.map { line ->
        line
            .chunked(4)
            .map { it[1] }
    }.reversed()

    val stackLot = StackLot(size = stackCount, init = { ArrayDeque() })
    for (chars in stack) {
        chars.forEachIndexed { index, c ->
            if (c != ' ') stackLot[index].add(c)
        }
    }

    return stackLot
}

fun setupOperations(operations: List<String>): List<List<Int>> {
    return operations.map {
        it
            .replace("move ", "")
            .replace(" from ", ",")
            .replace(" to ", ",")
            .split(",").map { int -> int.toInt() }
    }
}

fun separateStackAndOperations(input: List<String>): Triple<List<String>, Int, List<String>> {
    val stack = mutableListOf<String>()
    val operations = mutableListOf<String>()

    var stillAddingStack = true

    input.forEach { line ->
        if (line == "") {
            stillAddingStack = false
        } else {
            if (stillAddingStack) {
                stack.add(line)
            } else {
                operations.add(line)
            }
        }
    }

    return Triple(stack.dropLast(1), stack.last().trim().last().digitToInt(), operations)
}
