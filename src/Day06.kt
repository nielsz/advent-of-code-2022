fun main() {
    fun part1(input: String): Int {
        return input.firstUniquePartWithSize(4).first
    }

    fun part2(input: String): Int {
        return input.firstUniquePartWithSize(14).first
    }

    val input = readInput("Day06").first()

    println(part1(input)) // 1210
    println(part2(input)) // 3476
}

private fun String.onlyUniqueCharacters(): Boolean {
    val characters = HashSet<Char>()
    for (c in this) {
        if (characters.contains(c)) return false
        characters.add(c)
    }

    return true
}

private fun String.firstUniquePartWithSize(size: Int): Pair<Int, String> {
    var offset = size
    return windowed(size = size)
        .map { Pair(offset++, it) }
        .first { it.second.onlyUniqueCharacters() }
}
