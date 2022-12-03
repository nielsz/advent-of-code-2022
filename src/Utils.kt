import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')


/**
 * a -> 1
 * z -> 26
 * A -> 27
 * Z ->
 */
fun Char.getNumericValue(): Int {
    return if (isLowerCase()) {
        this - 'a' + 1
    } else {
        this - 'A' + 27
    }
}

