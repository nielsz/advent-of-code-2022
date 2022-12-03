fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            line.compartments().getNonUniqueValues().getPriority()
        }
    }

    fun part2(input: List<String>): Int {
        return input
            .windowed(size = 3, step = 3)
            .sumOf { group ->
                getNonUniqueValues(group).first().getNumericValue()
            }
    }

    val input = readInput("Day03")
    println(part1(input)) // 7428
    println(part2(input)) // 2650
}


typealias Rucksack = String
typealias Compartment = String

fun Rucksack.compartments(): Pair<Compartment, Compartment> {
    return Pair(substring(0, length / 2), substring(length / 2))
}


fun getNonUniqueValues(group: List<String>): Set<Char> {
    val result = hashSetOf<Char>()

    for (c in group[0]) {
        result.add(c)
    }

    for (i in 1..group.lastIndex) {
        val rucksack = group[i]
        for (c in result.clone() as Set<Char>) {
            if (!rucksack.contains(c)) result.remove(c)
        }
    }

    return result
}

private fun Pair<Compartment, Compartment>.getNonUniqueValues(): Set<Char> {
    val result = hashSetOf<Char>()

    val firstC = first.toSet()
    val secondC = second.toSet()

    for (c in firstC) {
        if (secondC.contains(c)) result.add(c)
    }

    for (c in secondC) {
        if (firstC.contains(c)) result.add(c)
    }

    return result
}

private fun Set<Char>.getPriority() = sumOf { it.getNumericValue() }

