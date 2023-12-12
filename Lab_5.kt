import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

data class Person(
     var name: String = "Vasyl",
     private var surname: String = "Jahiv",
     private var birthDate: LocalDate = LocalDate.of(2000, 1, 1),
     var educationForm: Education = Education.Bachelor
 ): IRateAndCopy {
    override val Rate: Double
        get() = 0.0

    override fun DeepCopy(): Any {
        return copy()
    }

    constructor(name: String, surname: String, birthDate: String, educationForm: Education) : this(
        name,
        surname,
        LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
        educationForm
    )

    val firstName: String
        get() = name

    val lastName: String
        get() = surname

    val birthDateTime: LocalDate
        get() = birthDate

    val yearOfBirth: Int
        get() = birthDate.year

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Person -> this.name == other.name && this.surname == other.surname && this.birthDate == other.birthDate
            else -> false
        }
    }

    override fun hashCode(): Int {
        return Objects.hash(name, surname, birthDate)
    }

    override fun toString(): String {
        return "Person(name='$name', surname='$surname', birthDate=$birthDate, educationForm=$educationForm)"
    }

    fun toShortString(): String {
        return "Person(name='$name', surname='$surname')"
    }

    companion object {
        fun generatePerson(): Person {
            val random = Random()
            val randomName = (1..5).map { ('a' + random.nextInt(26)).toString() }.joinToString("")
            val randomSurname = (1..5).map { ('a' + random.nextInt(26)).toString() }.joinToString("")
            val randomBirthDate = LocalDate.of(
                random.nextInt(30) + 1970,
                random.nextInt(12) + 1,
                random.nextInt(28) + 1
            )

            val randomEducationForm = Education.values().random()

            return Person(randomName, randomSurname, randomBirthDate, randomEducationForm)
        }
    }
}
import java.util.*

data class Student(
    var tests: List<Test> = listOf(),
    var exams: List<Exam> = listOf(),
    val person: Person = Person()
){

    val averageMark: Double
        get() {
            val totalPoints = exams.sumOf { it.grade }
            val numberOfExams = exams.size
            return if (numberOfExams > 0) totalPoints.toDouble() / numberOfExams else 0.0
        }

    override fun toString(): String {
        return "Student(person='$person', tests='$tests', exams='$exams', averageMark=$averageMark)"
    }

    companion object {
        private val random = Random()

        fun generateStudent(): Student {
            val person = Person.generatePerson()
            val numberOfTests = random.nextInt(5) + 1
            val numberOfExams = random.nextInt(5) + 1

            val tests = (1..numberOfTests).map { Test() }
            val exams = (1..numberOfExams).map { Exam() }

            return Student(
                tests = tests,
                exams = exams,
                person = person
            )
        }
    }
}
import java.util.*

class StudentCollection {
    private val students = mutableListOf<Student>()

    fun addDefaults() {
        for (i in 1..5) {
            students.add(Student.generateStudent())
        }
    }

    fun addStudents(vararg newStudents: Student) {
        students.addAll(newStudents)
    }

    fun sortByName() {
        students.sortBy { it.person.firstName }
    }

    fun sortByBirthDate() {
        students.sortBy { it.person.birthDateTime }
    }

    fun sortByAverageMark() {
        students.sortWith(StudentAverageMarkComparator())
    }

    fun getMaxAverageMark(): Double {
        return students.maxOfOrNull { it.averageMark } ?: 0.0
    }

    fun filterSpecialists(): List<Student> {
        return students.filter { it.person.educationForm == Education.Specialist }
    }

    fun groupByAverageMark(): Map<Double, List<Student>> {
        return students.groupBy { it.averageMark }
    }

    override fun toString(): String {
        return students.joinToString("\n")
    }

    private class StudentAverageMarkComparator : Comparator<Student> {
        override fun compare(o1: Student, o2: Student): Int {
            return o1.averageMark.compareTo(o2.averageMark)
        }
    }
}
import kotlin.system.measureTimeMillis

class TestCollection(private val numberOfElements: Int) {
    val peopleList = mutableListOf<Person>()
    private val stringList = mutableListOf<String>()
    val personStudentMap = mutableMapOf<Person, Student>()
    val stringStudentMap = mutableMapOf<String, Student>()
    init {
        generateCollections()
    }
    private fun generateCollections(){
        repeat(numberOfElements){
            val student = Student.generateStudent()
            val person = Person(
                name = student.person.firstName,
                surname = student.person.lastName,
                birthDate = student.person.birthDateTime,
                educationForm = student.person.educationForm
            )
            peopleList.add(person)
            stringList.add(student.toString())
            personStudentMap[person] = student
            stringStudentMap[student.toString()] = student
        }
    }
    companion object{
        fun measureSearchTime(testCollection: TestCollection){
            val firstElement = testCollection.peopleList.first()
            val centralElement = testCollection.peopleList[testCollection.peopleList.size / 2]
            val lastElement = testCollection.peopleList.last()
            val nonExistingElement = Student.generateStudent()

            val searchTimePeopleList = measureTimeMillis {
                testCollection.peopleList.containsAll(listOf(firstElement, centralElement, lastElement, nonExistingElement))
            }
            println("Search time in peopleList: $searchTimePeopleList ms")

            val searchTimeStringList = measureTimeMillis {
                testCollection.stringList.containsAll(listOf(firstElement.toString(), centralElement.toString(), lastElement.toString(), nonExistingElement.toString()))
            }
            println("Search time in stringList: $searchTimeStringList ms")

            val searchTimePersonStudentMap = measureTimeMillis {
                val elementsToCheck = listOf(firstElement, centralElement, lastElement, nonExistingElement)
                val allElementsAreKeys = elementsToCheck.all { testCollection.personStudentMap.keys.contains(it) }
                println("All elements are keys in personStudentMap: $allElementsAreKeys")
            }
            println("Search time in personStudentMap: $searchTimePersonStudentMap ms")

            val searchTimeStringStudentMap = measureTimeMillis {
                val elementsToCheck = listOf(firstElement, centralElement, lastElement,nonExistingElement.toString())
                val allElementsAreKeys = elementsToCheck.all { testCollection.stringStudentMap.keys.contains(it) }
                println("All elements are keys in stringStudentMap: $allElementsAreKeys")
            }
            println("Search time in stringStudentMap: $searchTimeStringStudentMap ms")
        }
    }
}
import java.time.*
import kotlin.system.measureTimeMillis

enum class Education {
    Bachelor, Specialist, Master, PhD
}

data class Test(
    val subject: String = "Math",
    val date: LocalDate = LocalDate.of(2022, 1, 1),
    val isPassed: Boolean = true
)
data class Exam(
    val subject: String = "Physics",
    val date: LocalDate = LocalDate.of(2022, 1, 1),
    val grade: Int = 90
)

interface IRateAndCopy{
    val Rate: Double
    fun DeepCopy(): Any
}

fun main() {
    // 1. Створити об'єкт типу StudentCollection
    val studentCollection = StudentCollection()

    // Додати до колекції кілька різних елементів типу Student
    studentCollection.addStudents(
        Student.generateStudent(),
        Student.generateStudent(),
        Student.generateStudent()
    )

    // Вивести об'єкт StudentCollection
    println("Initial StudentCollection:")
    println(studentCollection)

    studentCollection.addDefaults()

    // 2. Викликати методи, які виконують сортування списку List<Student>
    studentCollection.sortByName()
    println("\nSorted by Name:")
    println(studentCollection)

    studentCollection.sortByBirthDate()
    println("\nSorted by Birth Date:")
    println(studentCollection)

    studentCollection.sortByAverageMark()
    println("\nSorted by Average Mark:")
    println(studentCollection)

    // 3. Викликати методи класу StudentCollection, що виконують операції зі списком List<Student>
    val maxAverageMark = studentCollection.getMaxAverageMark()
    println("\nMax Average Mark: $maxAverageMark")

    val specialists = studentCollection.filterSpecialists()
    println("\nSpecialists:")
    println(specialists)

    val groupedByAverageMark = studentCollection.groupByAverageMark()
    println("\nGrouped by Average Mark:")
    groupedByAverageMark.forEach { (averageMark, students) ->
        println("Average Mark: $averageMark, Students: $students")
    }

    // 4. Створити об'єкт типу TestCollections
    val testCollection = TestCollection(5)

    // Call measureSearchTime to measure search time
    TestCollection.measureSearchTime(testCollection)

    // 5. Викликати метод для пошуку в колекціях
    val firstElement = testCollection.peopleList.first()
    val centralElement = testCollection.peopleList[testCollection.peopleList.size / 2]
    val lastElement = testCollection.peopleList.last()
    val nonExistingElement = Person.generatePerson()

    val searchTimePeopleList = measureTimeMillis {
        testCollection.peopleList.containsAll(
            listOf(firstElement, centralElement, lastElement, nonExistingElement)
                .map { it as Person }
        )
    }
    println("\nSearch Time in People List: $searchTimePeopleList ms")

    val searchTimePersonStudentMap = measureTimeMillis {
        testCollection.personStudentMap.keys.containsAll(
            listOf(firstElement, centralElement, lastElement, nonExistingElement)
                .map { it as Person }
        )
    }
    println("Search Time in Person Student Map: $searchTimePersonStudentMap ms")

    val searchTimeStringStudentMap = measureTimeMillis {
        testCollection.stringStudentMap.keys.containsAll(
            listOf(
                firstElement.toString(),
                centralElement.toString(),
                lastElement.toString(),
                nonExistingElement.toString()
            )
        )
    }
    println("Search Time in String Student Map: $searchTimeStringStudentMap ms")

}
