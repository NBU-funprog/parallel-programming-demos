import common._

object Start {
  var total = 0
  def sum(data: Array[Int], begin: Int, end: Int) = {
    data.slice(begin, end).foreach(total += _)
  }

  def sumDec(data: Array[Int], begin: Int, end: Int) = {
    data.slice(begin, end).reduce(_ + _)
  }

  
  def main(args: Array[String]): Unit = {
    val data = (0 until 10000) toArray;
    println(data.length)
    parallel(
        sum(data, 0, 5000), 
        sum(data, 5000, 10000))

//    sum(data, 0, 5000)
//    sum(data, 5001, 10000)
//    Console println (left + right)
    
//    sum(data, 0, data.length)
    Console println (sumDec(data, 0, data.length))
    Console println total
  }

}