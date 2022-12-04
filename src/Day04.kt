fun main() {
    fun part1(input: List<String>): Int {
        val result = input.map {
            val elves = it.split(",")
            val elf1 = elves[0].split("-").map { a -> a.toInt() }
            val elf2 = elves[1].split("-").map { a -> a.toInt() }
            val elf1range = IntRange(elf1[0], elf1[1])
            val elf2range = IntRange(elf2[0], elf2[1])
            Pair(elf1range, elf2range)
        }.map {
            if (it.first.overlaps(it.second)) 1 else 0
        }.sum()

        return result
    }

    fun part2(input: List<String>): Int {
        val result = input.map {
            val elves = it.split(",")
            val elf1 = elves[0].split("-").map { a -> a.toInt() }
            val elf2 = elves[1].split("-").map { a -> a.toInt() }
            val elf1range = IntRange(elf1[0], elf1[1])
            val elf2range = IntRange(elf2[0], elf2[1])
            Pair(elf1range, elf2range)
        }.map {
            if (it.first.overlapsAnything(it.second)) 1 else 0
        }.sum()

        return result
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

private fun IntRange.overlaps(other: IntRange): Boolean {
    return this.first <= other.first && this.last >= other.last ||
            other.first <= this.first && other.last >= this.last
}

private fun IntRange.overlapsAnything(other: IntRange): Boolean {
    return this.intersect(other).isNotEmpty()
}
