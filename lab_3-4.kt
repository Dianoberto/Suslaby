import java.util.*

 class Relaxation(
    var person: Person = Person(), // Default person
    var relaxationType: String = "Vacation", // Default relaxation type
    var rating: Double = 5.0 // Default rating
): IRateAndCopy
    {
        override val Rate: Double
            get() = rating

        override fun DeepCopy(): Any {
            return Relaxation(person.DeepCopy() as Person, relaxationType, rating)
        }
    // Overridden toString method
    override fun toString(): String {
        return "Relaxation(person=$person, relaxationType='$relaxationType', rating=$rating)"
    }
}
import java.util.*
import java.time.LocalDate
import java.util.Objects

open class City(
    var cityName: String = "Default city",
    protected var cityOpeningDate: LocalDate = LocalDate.now(),
    protected var numberOfBases: Int = 0
) {
    // Constructor with parameters
    constructor(cityName: String, openingDate: String, numberOfBases: Int) : this(
        cityName,
        LocalDate.parse(openingDate),
        numberOfBases
    )
    fun servicesNotInRelaxations(services: List<Relaxation>): List<Relaxation> {
        // Припустимо, що в relaxations містяться всі доступні послуги, а services - список видів відпочинку
        return relaxations.filterNot { it in services }
    }


    private val relaxations: ArrayList<Relaxation> = ArrayList()

    open fun addRelaxation(vararg newRelaxations: Relaxation) {
        relaxations.addAll(newRelaxations)
    }
    // Властивість для доступу до поля з кількістю баз відпочинку
    var NumberOfBase: Int
        get() = numberOfBases
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException("Number of bases cannot be negative.")
            }
            numberOfBases = value
        }

    // Перевизначення методу DeepCopy
    open fun DeepCopy(): Any {
        return City(cityName, cityOpeningDate, numberOfBases)
    }

    // Перевизначення методів для рівності та хеш-коду
    override fun equals(other: Any?): Boolean {
        return when (other) {
            is City -> this.cityName == other.cityName && this.cityOpeningDate == other.cityOpeningDate &&
                    this.numberOfBases == other.numberOfBases
            else -> false
        }
    }

    override fun hashCode(): Int {
        return Objects.hash(cityName, cityOpeningDate, numberOfBases)
    }

    // Перевизначення методу для формування рядка
    override fun toString(): String {
        return "City(cityName='$cityName', openingDate=$cityOpeningDate, numberOfBases=$numberOfBases)"
    }
}
import java.time.LocalDate
import java.util.*

class RecreationBase(
    var baseName: String = "Default Base",
    var workingSeason: WinterSeason = WinterSeason.December,
    var notWorking: AllSeason = AllSeason.April,
    var openingDate: LocalDate = LocalDate.now(),
    var workingDays: Int = 5,
    var relaxations: Array<Relaxation> = emptyArray()
): City(baseName, openingDate, 0), IRateAndCopy
{
    constructor(
        baseName: String,
        workingSeason: WinterSeason,
        notWorking: AllSeason,
        openingDate: LocalDate,
        workingDays: Int
    ) : this(baseName, workingSeason, notWorking, openingDate, workingDays, emptyArray())

    fun WorkingRelaxationsWithRating(ratingThreshold: Double): List<Relaxation> {
        return WorkingRelaxations.filter { it.Rate > ratingThreshold }
    }
    fun WorkingRelaxationsWithName(searchString: String): List<Relaxation> {
        return WorkingRelaxations.filter { it.relaxationType.contains(searchString, ignoreCase = true) }
    }

    // Властивість для доступу до поля зі списком видів відпочинку у робочі дні
    var WorkingRelaxations: Array<Relaxation>
        get() = relaxations
        set(value) {
            relaxations = value
        }

    // Властивість для доступу до поля з кількістю робочих днів
    var WorkingDay: Int
        get() = workingDays
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException("Number of working days cannot be negative.")
            }
            workingDays = value
        }
    // Перевизначені методи для інтерфейсу IRateAndCopy
    override val Rate: Double
        get() = relaxations.map { it.Rate }.average()

    override fun DeepCopy(): Any {
        // Реалізація глибокого копіювання
        val copiedRelaxations = ArrayList<Relaxation>()
        for (relaxation in relaxations) {
            copiedRelaxations.add(relaxation.DeepCopy() as Relaxation)
        }

        return super.DeepCopy()
    }

    // Властивість для доступу до поля зі списком видів відпочинку
    var Relaxation: Array<Relaxation>
        get() = relaxations
        set(value) {
            relaxations = value
        }


    var base: String = baseName
        get() = field
        set(value) {
            field = value
        }

    var Season: WinterSeason = workingSeason
        get() = field
        set(value) {
            field = value
        }

    var opening: LocalDate = openingDate
        get() = field
        set(value) {
            field = value
        }

    var working: Int = workingDays
        get() = field
        set(value) {
            field = value
        }

    var relax: Array<Relaxation> = relaxations
        get() = field
        set(value) {
            field = value
        }
    // Property with custom get method calculating the average rating
    val averageRating: Double
        get() {
            if (relaxations.isEmpty()) return 0.0
            return relaxations.map { it.rating }.average()
        }

    // Indexer returning true if the provided WinterSeason matches the current season
    operator fun get(seasonToCheck: WinterSeason): Boolean {
        return Season == seasonToCheck
    }

    // Method for adding Relaxation objects to the relaxations array
    override fun addRelaxation(vararg newRelaxations: Relaxation) {
        relaxations += newRelaxations
    }

    // Overridden toString method
    override fun toString(): String {
        return "RecreationBase(baseName='$baseName', season=$Season, openingDate=$openingDate, " +
                "workingDays=$workingDays, relaxations=${relaxations.contentToString()}, " +
                "averageRating=$averageRating)"
    }

    // Virtual method returning a short string without the list of relaxations
    open fun toShortString(): String {
        return "RecreationBase(baseName='$baseName', season=$Season, openingDate=$openingDate, " +
                "workingDays=$workingDays, averageRating=$averageRating)"
    }

}
import java.time.LocalDate
import java.time.DateTimeException
import java.util.Objects


data class Person(private var name: String = "John", // Default name
                  private var surname: String = "Doe", // Default surname
                  private var birthDate: LocalDate = LocalDate.of(2000, 1, 1)// Default birth date
): IRateAndCopy
{   // Перевизначені методи для інтерфейсу IRateAndCopy
    override val Rate: Double
        get() = 0.0  // Замініть це значення на відповідне

    override fun DeepCopy(): Any {
        return copy()   // Реалізація глибокого копіювання
    }
    // Перевизначені методи для рівності та хеш-коду
    override fun equals(other: Any?): Boolean {
        return when(other){   // Реалізація порівняння для рівності
            is Person -> this.name == other.name && this.surname == other.surname && this.birthDate == other.birthDate
            else -> false
        }
    }

    override fun hashCode(): Int {   // Реалізація хеш-коду
        return Objects.hash(name, surname, birthDate)
    }
    // Constructor with three parameters
    constructor(name: String, surname: String, birthDate: String) : this(
    name,
    surname,
    LocalDate.parse(birthDate)
    )

    // Default constructor
    constructor() : this("", "", LocalDate.now())

    var firstName: String
        get() = name
        set(value) {
            firstName = value
        }

    var lastName: String
        get() = surname
        set(value) {
            lastName = value
        }

    var birthDateTime: LocalDate
        get() = birthDate
        set(value) {
            birthDate = value
        }

    // Properties with custom get and set methods
    var yearOfBirth: Int
        get() = birthDate.year
        set(value) {
            // Setting the year and handling potential DateTimeException
            try {
                birthDate = birthDate.withYear(value)
            } catch (e: DateTimeException) {
                println("Invalid year: $value")
            }
        }

    // Overridden toString method
    override fun toString(): String {
        return "Person(name='$name', surname='$surname', birthDate=$birthDate)"
    }

    // Virtual method returning a short string
    open fun toShortString(): String {
        return "Person(name='$name', surname='$surname')"
    }
}
import java.time.DateTimeException
import java.time.LocalDate

enum class WinterSeason {
    December, January, February
}
enum class AllSeason {
    March, April, May, June, July, August, September, Oсtober, November
}

interface IRateAndCopy {
    val Rate: Double
    fun DeepCopy(): Any
}

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

    // 1. Creating an object of RecreationBase and printing data using toShortString()
    val recreationBase = RecreationBase()
    println("RecreationBase Data (Short): ${recreationBase.toShortString()}")

    // 2. Printing values of the indexer for different WinterSeason values
    println("Indexer for December: ${recreationBase[WinterSeason.December]}")
    println("Indexer for January: ${recreationBase[WinterSeason.January]}")
    println("Indexer for February: ${recreationBase[WinterSeason.February]}")

    // 3. Setting values for all properties, printing data using toString()
    recreationBase.baseName = "Winter Resort"
    recreationBase.Season = WinterSeason.January
    recreationBase.openingDate = LocalDate.of(2022, 12, 15)
    recreationBase.workingDays = 6
    println("RecreationBase Data: ${recreationBase.toString()}")

    // 4. Adding Relaxation objects and printing data using toString()
    val relaxation1 = Relaxation(Person("Alice", "Smith", LocalDate.of(1990, 5, 10)), "Skiing", 4.5)
    val relaxation2 = Relaxation(Person("Bob", "Johnson", LocalDate.of(1985, 8, 20)), "Snowboarding", 5.0)
    recreationBase.addRelaxation(relaxation1, relaxation2)
    println("RecreationBase Data after Adding Relaxations: ${recreationBase.toString()}")

    // 5. Comparing the time of operations on different types of arrays
    val relaxationArray = Array(1000000) { Relaxation() }
    val startTimeArray = System.currentTimeMillis()
    val ratingArray = relaxationArray.map { it.rating }.average()
    val endTimeArray = System.currentTimeMillis()
    println("Time taken for Array operation: ${endTimeArray - startTimeArray} milliseconds")

    val relaxation2DArray = Array(1000) { Array(1000) { Relaxation() } }
    val startTime2DArray = System.currentTimeMillis()
    val rating2DArray = relaxation2DArray.flatMap { it.toList() }.map { it.rating }.average()
    val endTime2DArray = System.currentTimeMillis()
    println("Time taken for 2D Array operation: ${endTime2DArray - startTime2DArray} milliseconds")

    val relaxationStaircaseArray = Array(1000) { index ->
        Array(index + 1) { Relaxation() }
    }
    val startTimeStaircaseArray = System.currentTimeMillis()
    val ratingStaircaseArray =
        relaxationStaircaseArray.flatMap { it.toList() }.map { it.rating }.average()
    val endTimeStaircaseArray = System.currentTimeMillis()
    println("Time taken for Staircase Array operation: ${endTimeStaircaseArray - startTimeStaircaseArray} milliseconds")
    fun main() {
        try {
            // Step 1: Create two RecreationBase objects and check if they are equal
            val base1 = RecreationBase(baseName = "Base1")
            val base2 = RecreationBase(baseName = "Base1")

            // Check if objects are equal and print hash codes if they are
            if (base1 == base2) {
                println("Objects are equal.")
                println("Hash code for base1: ${base1.hashCode()}")
                println("Hash code for base2: ${base2.hashCode()}")
            } else {
                println("Objects are not equal.")
            }

            // Step 2: Try setting invalid working days value
            try {
                base1.workingDays = -5
            } catch (ex: IllegalArgumentException) {
                println("Exception: ${ex.message}")
            }

            // Step 3: Create a City object, add relaxations, and print its data
            val city = City(cityName = "City1")
            val relaxation1 = Relaxation(person = Person(name = "Relax1"))
            val relaxation2 = Relaxation(person = Person(name = "Relax2"))
            city.addRelaxation(relaxation1, relaxation2)

            println("City information:")
            println(city)

            // Step 4: Print RecreationBase information for the City object
            println("RecreationBase information for the City:")
            println(city as RecreationBase) // Cast City to RecreationBase

            // Step 5: DeepCopy and modify City object, then print both original and copied objects
            val cityCopy = city.DeepCopy() as City
            println("Original City information:")
            println(city)
            println("Copied City information:")
            println(cityCopy)

            // Modify the original City
            city.cityName = "ModifiedCity"
            println("Original City information after modification:")
            println(city)
            println("Copied City information (should remain unchanged):")
            println(cityCopy)

            // Step 6: Iterate over RecreationBase relaxations with a rating greater than a specified value
            val ratingThreshold = 3.5
            println("Relaxations with rating greater than $ratingThreshold:")
            for (relaxation in (city as RecreationBase).WorkingRelaxationsWithRating(ratingThreshold)) {
                println(relaxation)
            }

            // Step 7: Iterate over RecreationBase relaxations with a specified string in their name
            val searchString = "Relax"
            println("Relaxations with name containing '$searchString':")
            for (relaxation in (city as RecreationBase).WorkingRelaxationsWithName(searchString)) {
                println(relaxation)
            }
        } catch (ex: Exception) {
            println("An unexpected error occurred: ${ex.message}")
        }
    }

}
