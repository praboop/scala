import scala.collection.mutable.ArrayBuffer
import scala.math._

object Test {

  var x:String = "mississippi"
  x.toSeq.distinct.unwrap
  "rhine".toSeq.permutations.toArray
  "ABC".sum
  1.to(10).foreach(println)

  for (i <- 0 to 10 ) yield i%3

  val buffer = ArrayBuffer(1,3,-1,-5,4,7,-8)
  println("Check: " + buffer.mkString("+", "$", "*"))

}