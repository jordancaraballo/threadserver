/*
  Program to calculate goldbach function from scala book on the threading section
  This script does it using atomic objects and methods.
  Author: Jordan A Caraballo Vega, Professor Jose Sotero Esteva
*/
package multithreading2
import scala.collection.parallel.mutable.ParArray

object GoldsbachSimpleAtomic extends App {

  /* default function given by exercise b from scala threading book */
  def goldbach(num:Int):Int = {
    var ret = 0
    var n = num
    while (n > 1) {
      n = if (n % 2 == 1) 3 * n - 1 else n / 2
      ret += 1
    }
    ret
  }

  var start = System.nanoTime() // set starting time
  var integerArray = ParArray(2,4,6,8,11,15) // define atomic array

  println("Initial Array: " + integerArray.mkString(","))
  // calculate goldbach function and get the max using atomic methods
  println("Biggest val: "   + integerArray.par.map(x => goldbach(x)).par.reduce(_ max _))
  println("Time taken: "    + (System.nanoTime() - start) /1e9)
}
