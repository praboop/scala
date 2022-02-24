import scala.collection.mutable.ArrayBuffer

object tup {

  ("New York").partition(_.isUpper)

  val buffer = ArrayBuffer(1,3,-1,-5,4,7,-8)

  val (neg, pos) = (buffer).partition(_ < 0)

  val result = pos
  result += neg(0)

  result

}