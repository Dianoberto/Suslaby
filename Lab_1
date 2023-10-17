import java.awt.Point
import kotlin.math.*

class Ex1 {  //var 4
    fun formula(x: Double, a: Double): Double{
        val y = ((x.pow(3) + 2*a*x + 3) / (x - 1).pow(2)) + (cos(x.pow(2)) / (a + 2))
    return y
    }
}
class Ex2{   //var 4
    fun kvad(n: Int){
        var found = false
        for (i in 1..n){
            val square = i*i
            val end = i.toString().takeLast(i.toString().length)
            val squareEnd = square.toString().takeLast(i.toString().length)
            if (end == squareEnd){
                println("$i ($square)")
            found = true  }
            if(!found){ println("This number hasn`t anything you`re looking for") }
        }
    }
}
data class Ex3(val x: Int, val y: Int){    //var 9
    fun distance(other: Ex3): Double{
        val dx = other.x - this.x
        val dy = other.y - this.y  // function for calculating the distance between two points given by their coordinates
        return sqrt((dx * dx + dy * dy).toDouble())
    }
}
open class One(){
    var p = 8
    var t = 20
    open fun add(){
        val res = p + t
        println(res)
    }
}
class Two: One(){
  override fun add(){ val res = p - t
  println(res)}
    }


fun main(){
    val ex4 = Two()
    ex4.add()
    println("Exersize one: ")
    val ex1 = Ex1()
    println("Enter the value of x: ")
    val xInput = readLine()?.toDoubleOrNull()
    println("Enter the value of a: ")
    val aInput = readLine()?.toDoubleOrNull()

    if (xInput == null || aInput == null){
        println("Invalid input. try again")
    } else {
        val res1 = ex1.formula(xInput, aInput)
        println("Result: $res1")
    }
/*----------------------------------------------------------------------*/
    println("Excersize two: ")
    val ex2 = Ex2()
    println("Enter the value of n (n should be positive): ")
    val nInput = readLine()?.toIntOrNull()

    if (nInput == null || nInput <= 0){
        println("Invalid input. try again")
    } else {
    ex2.kvad(nInput)
    }
/*----------------------------------------------------------------------*/
/*fun distance(point1: Point, point2: Point): Double{
    val dx = point2.x - point1.x
    val dy = point2.y - point1.y  // function for calculating the distance between two points given by their coordinates
    return sqrt((dx * dx + dy * dy).toDouble())
}*/
    println("Excersize three: ")
   val verticals = mutableListOf<Point>()
    println("Enter the coordinates of the 10 vertices (x1 y1, x2 y2, ..., x10 y10):")
    for(i  in 1..10){
        println("Enter coordinates $i: ")
        val input = readLine()?.split(" ")

        if (input == null || input.size != 2){
            println("Invalid input. Please enter two space-separated coordinates.")
            return }
        val x = input[0].toIntOrNull()
        val y = input[1].toIntOrNull()

        if (x == null || y == null){
            println("Invalid input. Please enter valid numeric coordinates.")
            return }
        verticals.add(Point(x, y))
    }
    var perimeter = 0.0
    for (i in 0 until verticals.size - 1) {
        perimeter +=verticals[i].distance(verticals[i + 1])
    }
    perimeter += verticals.last().distance(verticals.first())
    println("Perimeter of the decagon: $perimeter")
}
