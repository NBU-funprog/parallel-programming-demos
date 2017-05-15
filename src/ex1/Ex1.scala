package ex1

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

object Ex1 {
  
  val PAR_LEVEL = 5

  def main(args: Array[String]): Unit = {
    val data = (1 to 10000) toArray;
    
    val threads = new Array[Thread](PAR_LEVEL)

    for (i <- 0 until PAR_LEVEL) {
      val (begin, end) = (i * 2000, (i + 1) * 2000)
      threads(i) = new Thread(new ArraySumTask(data, begin, end))
      threads(i).start
    }

    for (t <- threads) t.join

    println(total)
  }

  val monitor = new Object
  var total = 0

  class ArraySumTask(data: Array[Int], begin: Int, end: Int) 
  extends Runnable {
    override def run = 
      for (i <- begin until end) monitor.synchronized(total += data(i)) 
  }
} 