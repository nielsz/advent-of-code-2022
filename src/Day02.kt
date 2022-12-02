fun main() {
    fun getBasicScore(their: Char, mine: Char): Int {
        return if (their != mine) {
            when {
                their == 'A' && mine == 'B' -> 6
                their == 'B' && mine == 'C' -> 6
                their == 'C' && mine == 'A' -> 6
                else -> 0
            }
        } else 3
    }


    fun getScorePart1(their: Char, mine: Char): Int {
        return mine - 'A' + 1 + getBasicScore(their, mine)
    }

    fun part1(input: List<String>): Int {
        var totalScore = 0

        for (line in input) {
            totalScore += getScorePart1(line[0], Char(line[2].code - 23))
        }
        return totalScore

    }

    fun getScorePart2(their: Char, mine: Char): Int {
        return when (mine) {
            'X' -> when (their) {
                'A' -> 3
                'B' -> 1
                else -> 2
            }

            'Y' -> 3 + when (their) {
                'A' -> 1
                'B' -> 2
                else -> 3
            }

            else -> 6 + when (their) {
                'A' -> 2
                'B' -> 3
                else -> 1
            }
        }
    }

    fun part2(input: List<String>): Int {
        var totalScore = 0

        for (line in input) {
            totalScore += getScorePart2(line[0], line[2])
        }
        return totalScore
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
