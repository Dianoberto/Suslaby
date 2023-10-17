import kotlin.math.*
import kotlin.random.*

class Ex1 {
    fun solve1() {
        print("Enter the number of elements in the array: ")
        val n = readLine()?.toIntOrNull()
        if (n == null || n <= 0) {
            println("Invalid input for the number of elements. Please enter a positive integer.")
            return
        }
        val array = DoubleArray(n)
        for (i in 0 until n) {
            print("Enter element $i: ")
            val element = readLine()?.toDoubleOrNull()
            if (element == null) {
                println("Invalid input for element $i. Please enter a valid real number.")
                return
            }
            array[i] = element
        }
        print("Enter the lower bound A: ")
        val A = readLine()?.toDoubleOrNull()
        if (A == null) {
            println("Invalid input for A. Please enter a valid real number.")
            return
        }
        print("Enter the upper bound B: ")
        val B = readLine()?.toDoubleOrNull()
        if (B == null) {
            println("Invalid input for B. Please enter a valid real number.")
            return
        }
        // Step 1: Count elements equal to zero
        val zeroCount = array.count { it == 0.0 }
        // Step 2: Calculate the sum of elements in the range [A, B]
        var sumInRange = 0.0
        // Calculate the sum of elements in the range [A, B]
        for (element in array) {
            if (element >= A && element <= B) {
                sumInRange += element
            }
        }
        // Step 3: Convert all array values into absolute values and sort in descending order
        for (i in 0 until array.size - 1) {
            var maxIndex = i
            for (j in i + 1 until array.size) {
                if (Math.abs(array[j]) > Math.abs(array[maxIndex])) {
                    maxIndex = j
                }
            }
            val temp = array[i]
            array[i] = array[maxIndex]
            array[maxIndex] = temp
        }

        // Output the results
        println("Number of elements equal to zero: $zeroCount")
        println("Sum of elements in the range [$A, $B]: $sumInRange")
        println("Sorted array in descending order of absolute values: ${array.joinToString(", ")}")
    }
}
class Ex2 {
    fun solve2() {
        print("Enter the number of rows: ")
        val numRows = readLine()?.toIntOrNull()
        if (numRows == null || numRows <= 0) {
            println("Invalid input for the number of rows. Please enter a positive integer.")
            return
        }
        print("Enter the number of columns: ")
        val numCols = readLine()?.toIntOrNull()
        if (numCols == null || numCols <= 0) {
            println("Invalid input for the number of columns. Please enter a positive integer.")
            return
        }
        val matrix = Array(numRows) { IntArray(numCols) }
        // Generate a random matrix
        val random = Random.Default
        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                matrix[i][j] = random.nextInt(10) // Adjust the range as needed
            }
        }

        // Calculate the number of rows with at least one zero element
        val rowsWithZero = matrix.count { row -> row.any { it == 0 } }
        // Calculate the column with the longest series of identical elements
        var maxIdenticalCount = 0
        var columnIndex = -1
        for (j in 0 until numCols) {
            var currentIdenticalCount = 1
            for (i in 1 until numRows) {
                if (matrix[i][j] == matrix[i - 1][j]) {
                    currentIdenticalCount++
                } else {
                    currentIdenticalCount = 1
                }
                if (currentIdenticalCount > maxIdenticalCount) {
                    maxIdenticalCount = currentIdenticalCount
                    columnIndex = j
                }
            }
        }
        // Output the results
        println("Randomly generated matrix:")
        for (row in matrix) {
            println(row.joinToString(" "))
        }
        println("Number of rows with at least one zero element: $rowsWithZero")
        if (columnIndex != -1) {
            println("Column with the longest series of identical elements: $columnIndex")
        } else {
            println("No identical elements found in any column.")
        }
    }
}
class Ex3 {
    private fun extractCharactersAfterColon(text: String): String {
        val colonIndex = text.indexOf(':')
        return if (colonIndex != -1) text.substring(colonIndex + 1) else ""
    }
    private fun countSentencesWithOddWords(text: String): Int {
        val sentences = text.split("[.!?]+".toRegex())
        return sentences.count { sentence ->
            val words = sentence.trim().split("\\s+".toRegex())
            words.size % 2 != 0
        }
    }
    private fun removeWordsAfterCommas(text: String): String {
        return text.replace(",\\s*\\w+".toRegex(), ",")
    }
    fun solve3() {
        print("Enter a text line: ")
        val inputText = readLine() ?: return
        // Extract characters after the first ":"
        val charactersAfterColon = extractCharactersAfterColon(inputText)
        // Count sentences with an odd number of words
        val oddSentenceCount = countSentencesWithOddWords(charactersAfterColon)
        // Remove words after commas
        val sanitizedText = removeWordsAfterCommas(inputText)

        // Output the results
        println("Characters after the first \":\": $charactersAfterColon")
        println("Number of sentences with an odd number of words: $oddSentenceCount")
        println("Text with words after commas removed: $sanitizedText")
    }
}

fun main() {
    while (true) {
        println("Choose a task to execute:")
        println("1. Task 1")
        println("2. Task 2")
        println("3. Task 3")
        println("4. Exit")
        print("Enter the task number: ")
        when (readLine()?.toIntOrNull()) {
            1 -> {
                val ex1 = Ex1()
                ex1.solve1()
            }
            2 -> {
                val ex2 = Ex2()
                ex2.solve2()
            }
            3 -> {
                val ex3 = Ex3()
                ex3.solve3()
            }
            4 -> return
            else -> println("Invalid task number. Please enter a valid task number.")
        }
    }
}
