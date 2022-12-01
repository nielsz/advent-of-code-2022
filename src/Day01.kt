fun main() {
    fun part1(input: List<String>): Int {
        return getLargestStack(input)
    }

    fun part2(input: List<String>): Int {
        return getStacks(input)
            .sortedDescending()
            .take(3)
            .sum()
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

private fun getLargestStack(input: List<String>): Int {
    var largest = 0
    var currentStack = 0
    for (line in input) {
        if (line.isNotEmpty()) {
            currentStack += line.toInt()
        } else {
            largest = maxOf(largest, currentStack)
            currentStack = 0
        }
    }

    largest = maxOf(largest, currentStack)
    return largest
}

private fun getStacks(input: List<String>): List<Int> {
    val list = mutableListOf<Int>()

    var currentStack = 0
    for (line in input) {
        if (line.isNotEmpty()) {
            currentStack += line.toInt()
        } else {
            list.add(currentStack)
            currentStack = 0
        }
    }

    list.add(currentStack)
    return list
}
