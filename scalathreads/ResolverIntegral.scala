/* ResolverIntegral.scala
    Solves the integral of a predetermined function in an interval given
    by the user.
    Input: Two integers for the interval
   
   Jordan A Caraballo-Vega
   Operating Systems - Prof. Jose O. Sotero Esteva
 */

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.collection.parallel

object ResolverIntegral { 

    // Ask the user for the interval values
    //final val a: String = scala.io.StdIn.readLine("Ingrese entero a: ")
    //final val b: String = scala.io.StdIn.readLine("Ingrese entero b: ")
    //final val n: String = scala.io.StdIn.readLine("Ingrese entero n: ")
    
    final val a: String = "10"
    final val b: String = "20"
    final val n: String = "8"
        
    def main(args: Array[String]){ 
    
        // Print array
        println("Resolver Integral")
        
        // Solve delta x
        var deltaX: Float = (b.toFloat - a.toFloat) / n.toFloat
        println("Delta x: " + deltaX)

        // Create array with the interval values
        val intervalArray = 0 to n.toInt
        println("Interval Array: " + intervalArray.mkString(", "))
        
        // Define fxi
        var fxi = intervalArray.par.map(x => (x * deltaX) + a.toInt)
        println("fxi: " + fxi.mkString(", "))
        
        // Solve and sum array - non-parallel
        println("Non-parallel approx: " + fxi.map(x => ((x * x) + 2) - deltaX).reduce(_+_))
        
        // Solve and sum array - parallel
        println("Parallel approx: " + fxi.par.map(x => ((x * x) + 2) - deltaX).reduce(_+_))
        
    } 
    

}
