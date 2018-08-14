/* sumaCuadrados.scala
   Multiplicacion de valor al cuadrado con y sin parelelismo
   Input: Array predeterminado
   
   Jordan A Caraballo-Vega
   Operating Systems - Prof. Jose O. Sotero Esteva
 */

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import scala.collection.parallel

object sumaCuadrados { 

    // Define array of integers - maybe in the future ask the user for values
    var array1 = Array(2, 4, 6, 8, 10, 12, 14, 16)
    
    def main(args: Array[String]){ 
    
        // Print array
        println("Suma Potencia de 2")
        println("Initial Array: " + array1.mkString(","))
        
        // Non parallel way of suming all the values from the array
        println("Non-parallel sum: " + array1.map(x => x * x).reduce(_+_))
        
        // Parallel way of Suming all the values from the array
        println("Parallel sum: " + array1.par.map(x => x * x).reduce(_+_))
        
    } 
    

}
