object HelloWorld {
  import scala.collection.mutable.ArrayBuffer

  // Remove all but first negative number
  def rmNeg(data: ArrayBuffer[Int]): ArrayBuffer[Int] = {

    val indexes      = for (i <- 0 until data.length if data(i) < 0) yield i
    val firstDropped = indexes.drop(1)

    println("-ve idx list: " + indexes)
    println("reversed list: " + firstDropped.reverse)

    for (i <- firstDropped.reverse) {
      println("Befor removal of " + i + " array: " + data)
      data.remove(i)
      println("After removal of " + i + " array: " + data)
    }

    data
  }

  def rmNeg2(buffer: ArrayBuffer[Int]): IndexedSeq[Int] = {
    val idxToRemove = (for (z <- 0 until buffer.length if buffer(z) < 0) yield z).drop(1)
    for (m <- 0 until buffer.length if !idxToRemove.contains(m)) yield buffer(m)
  }

  println("Hello, world!") // prints Hello World

  val buffer = ArrayBuffer(1, 3, -1, -5, 4, 7, -8)
  println("Input  : " + buffer)
  println("Result1: " + rmNeg(buffer))
  println("Result2: " + rmNeg2(buffer))

  import scala.collection.Map
  var myMap: Map[String, String] = Map("somes" -> "true")

  var x: Int = (1 / 600).ceil.toInt

  for (t <- 1 to 10) {
    println("X is " + x)
  }
  /*
    if (myMap.getOrElse("some", "false"))
      println("It is true");
    else
      println("It is false")
 */

}
