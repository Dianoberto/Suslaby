fun main() {
    operator fun Int.get(season: String): String {
        return when (this){
            1 -> "Січень"
            2 -> "Лютий"
            3 -> "Березень"
            4 -> "Квітень"
            5 -> "Травень"
            6 -> "Червень"
            7 -> "Липень"
            8 -> "Серпень"
            9 -> "Вересень"
            10 -> "Жовтень"
            11 -> "Листопад"
            12 -> "Грудень"
            else -> "Невідомий місяць"
        }
    }

    fun getMonthByIndex(input: String): String {
        val index = input.toIntOrNull()
        return index?.get("month") ?: "Невідомий місяць"
    }

    print("Введіть число від 1 до 12: ")
    val input = readLine()

    if (input != null) {
        val month = getMonthByIndex(input)
        println("Відповідний місяць: $month")
    } else {
        println("Некоректне введення. Будь ласка, введіть число від 1 до 12.")
    }
