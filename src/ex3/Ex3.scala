package ex3

import java.util.concurrent.RecursiveTask
import java.util.concurrent.ForkJoinPool

object Ex3 {
  def main(args: Array[String]): Unit = {
    val data = (1 to 10000) toArray
    
    val fjPool = ForkJoinPool.commonPool
    
    val result = fjPool.submit(new ArraySumTask(data, 0, data.length - 1))
    println(result.get)
  }

  class ArraySumTask(data: Array[Int], from: Int, to: Int) 
  extends RecursiveTask[Int] {
    override def compute: Int = {
      if (from == to) data(from)
      else if (from + 1 == to) data(from) + data(to)
      else {
        val middle = ((to - from) / 2 + from)
        val leftTask = new ArraySumTask(data, from, middle)
        val rightTask = new ArraySumTask(data, middle + 1, to)
        
        leftTask.fork

        rightTask.compute + leftTask.join 
      }
    }

  }
}

