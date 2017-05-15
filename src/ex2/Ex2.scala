package ex2

import java.util.concurrent.Executors
import java.util.concurrent.Callable
import java.util.concurrent.Future

object Ex2 {
  val PAR_LEVEL = 5
  def main(args: Array[String]): Unit = {
    val data = (1 to 10000) toArray

    // init the thread pool
    val threadPool = Executors.newFixedThreadPool(5)
    
    val futures =
      for (i <- 0 until PAR_LEVEL) yield {
        val (begin, end) = (i * 2000, (i + 1) * 2000)
        // submit the tasks
        threadPool.submit(new ArraySumTask(data, begin, end))
      }

    // get the result
    println(futures.map(_.get).sum)    

    // shutdown the pool
    threadPool.shutdown
  }
  
  class ArraySumTask(data: Array[Int], begin: Int, end: Int) 
  extends Callable[Int] {
    override def call = data.slice(begin, end).sum
  }
}