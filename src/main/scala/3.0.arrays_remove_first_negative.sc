import scala.collection.mutable.ArrayBuffer

  // Remove all but first negative number
  def rmNeg(data: ArrayBuffer[Int]): ArrayBuffer[Int] = {

    val indexes = for ( i <- 0 until data.length if data(i) < 0) yield i
    val firstDropped = indexes.drop(1)

    println("Index list: " + indexes)

    for (i <- firstDropped.reverse) data.remove(i)

    data
  }

  def rmNeg2(buffer: ArrayBuffer[Int]): IndexedSeq[Int] = {
    val idxToRemove = (for (z <- 0 until buffer.length if buffer(z) < 0) yield z).drop(1)
    for (m <- 0 until buffer.length if !idxToRemove.contains(m)) yield buffer(m)
  }

  val buffer = ArrayBuffer(1,3,-1,-5,4,7,-8)

  println ("Result: " + rmNeg(buffer))
  println("accum: " + rmNeg2(buffer))
