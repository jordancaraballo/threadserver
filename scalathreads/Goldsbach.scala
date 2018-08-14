/*
  Program to calculate goldbach function from scala book on the threading section
  Author: Jordan A Caraballo Vega, Professor Jose Sotero Esteva
*/
package multithreading2

object Goldsbach extends App {

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

  var integerArray = Array(2,4,6,8,11,15) // define array

  var finalArray = integerArray.map(x => goldbach(x))

  println("Initial Array: "         + integerArray.mkString(","))     // print initial array
  println("Final Array (Scores): "  + finalArray.mkString(","))       // print final array with scores
  println("Biggest val: "           + finalArray.reduceLeft(_ max _)) // get biggest value


  println("Time taken: " + (System.nanoTime() - start) /1e9)
}
