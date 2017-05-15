package ex5

object Ex5 {

  def main(args: Array[String]): Unit = {
    val data = (0 to 100000) toArray;
    
    val seqResult = data.seq.filter(isPrime(_)).map(Math.sqrt(_)).sum
    val parResult = data.par.filter(isPrime(_)).map(Math.sqrt(_)).sum

    execute("Seq", seqResult)
    execute("Par", parResult)
  }

  private def execute(identifier: String, task: => Double) = {
    val start = System.nanoTime()
    val result = task
    val end = System.nanoTime()

    println(s"[${identifier}] time: ${(end - start)} sec, result: ${result}")
  }

  def isPrime(number: Int) = !(2 until number).exists(number % _ == 0)
}