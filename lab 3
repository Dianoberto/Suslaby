import java.time.LocalDate
import java.time.DateTimeException

data class Person(private var name: String = "John", // Default name
                  private var surname: String = "Doe", // Default surname
                  private var birthDate: LocalDate = LocalDate.of(2000, 1, 1)) // Default birth date
{
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
class Relaxation(
    var person: Person = Person(), // Default person
    var relaxationType: String = "Vacation", // Default relaxation type
    var rating: Double = 5.0 // Default rating
)
    {
    // Overridden toString method
    override fun toString(): String {
        return "Relaxation(person=$person, relaxationType='$relaxationType', rating=$rating)"
    }
}
import java.time.LocalDate

class RecreationBase(
    var baseName: String = "Default Base",
    var workingSeason: WinterSeason = WinterSeason.December,
    var openingDate: LocalDate = LocalDate.now(),
    var workingDays: Int = 5,
    var relaxations: Array<Relaxation> = emptyArray()
) {
    constructor(
        baseName: String,
        workingSeason: WinterSeason,
        openingDate: LocalDate,
        workingDays: Int
    ) : this(baseName, workingSeason, openingDate, workingDays, emptyArray())
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
    fun addRelaxation(vararg newRelaxations: Relaxation) {
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

import java.time.DateTimeException
import java.time.LocalDate

enum class WinterSeason {
    December, January, February
}

fun main() {
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
}
