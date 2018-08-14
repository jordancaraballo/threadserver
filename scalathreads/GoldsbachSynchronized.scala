/*
  Program to calculate goldbach function from scala book on the threading section
  This script does it using threads in synchronized way.
  Author: Jordan A Caraballo Vega, Professor Jose Sotero Esteva
*/

package multithreading2
import java.lang.Thread

object GoldsbachSynchronized extends App {

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

  var integerArray = Array(2,4,6,8,11,15) // set array with integers
  val numThreads   = integerArray.length  // get the length of the array to setup number of threads
  var biggest      = 0                    // value to store biggest score from goldbach function

  /* Start threads in synchronize mode */
  val threads = Array.tabulate(numThreads)(i => new Thread {
    override def run():Unit = {
        var value = goldbach(integerArray(i))
        GoldsbachSynchronized.synchronized {
             if (biggest < value) biggest = value
        }

    }
  })
  threads.foreach(_.start) // start threads
  threads.foreach(_.join)  // make threads wait for each one of them

  println("Time taken: " + (System.nanoTime() - start) /1e9)
  println("Resultado: "  + biggest)

}
