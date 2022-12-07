fun main() {
    fun part1(input: List<String>): Int {
        val fileSystem = createFileSystem(input)
        return fileSystem.getDirectories()
            .filter { it.getSize() <= 100_000 }
            .sumOf { it.getSize() }
    }

    fun part2(input: List<String>): Int {
        val totalSize = 70_000_000
        val minSize = 30_000_000

        val fileSystem = createFileSystem(input)
        val usedSize = fileSystem.getCurrentDirectorySize() // 40_572_957
        val availableSize = totalSize - usedSize // 29_427_043
        val missingSize = minSize - availableSize // 572_957

        return fileSystem.getDirectories()
            .map { it.getSize() }
            .filter { it > missingSize }.minOf { it }
    }

    val input = readInput("Day07")
    println("size is: " + part1(input)) // 1447046
    println("size is: " + part2(input)) // 578710
}

fun createFileSystem(input: List<String>): FileSystem {
    val fileSystem = FileSystem()
    var readingDirectoryContents = false

    for (line in input) {
        if (line[0] == '$') {
            if (line.substring(2).startsWith("cd ")) {
                fileSystem.changeDirectory(line.substring(5))
                readingDirectoryContents = false
            } else if (line == "$ ls") {
                readingDirectoryContents = true
            }
        } else if (readingDirectoryContents) {
            fileSystem.addObject(line)
        }
    }

    fileSystem.changeDirectory("/")
    return fileSystem
}


class FileSystem {
    private val root = Directory("/", parent = null)
    private var workingDirectory = root

    fun changeDirectory(directory: String) {
        workingDirectory = when (directory) {
            "/" -> root
            ".." -> workingDirectory.parent!!
            else -> workingDirectory.getDirectory(directory)
        }
    }

    fun addObject(line: String) {
        workingDirectory.addObject(line)
    }

    fun getCurrentDirectorySize() = workingDirectory.getSize()
    fun getDirectories(): List<Directory> {
        return workingDirectory.getDirectories()
    }
}


data class Directory(val name: String, val parent: Directory?) : FileObject() {
    private val children = mutableListOf<FileObject>()

    fun getDirectories(): List<Directory> {
        val result = mutableListOf<Directory>()
        val childrenOfThisDirectory = children.filterIsInstance<Directory>()
        result.addAll(childrenOfThisDirectory)
        childrenOfThisDirectory.forEach {
            result.addAll(it.getDirectories())
        }
        return result
    }


    override fun getSize() = children.sumOf { it.getSize() }

    fun getDirectory(name: String): Directory {
        return children.filterIsInstance<Directory>().first { it.name == name }
    }

    fun addObject(line: String) {
        if (line.startsWith("dir")) {
            children.add(Directory(name = line.substring(4), parent = this))
        } else {
            val file = line.split(" ")
            children.add(File(name = file[1], filesize = file[0].toInt()))
        }
    }
}

data class File(val name: String, val filesize: Int) : FileObject() {
    override fun getSize() = filesize
}

abstract class FileObject {
    abstract fun getSize(): Int
}
