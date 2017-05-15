package ex4

object Ex4 {
  def main(args: Array[String]): Unit = {
    val seqSum = (0 to 10000).seq.reduce(_ - _)
    val par1Sum = (0 to 10000).par.reduce(_ - _)
    val par2Sum = (0 to 10000).par.reduce(_ - _)

    println(par1Sum)
    println(par2Sum)
  }
}