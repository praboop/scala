import scala.collection.mutable.ListBuffer

object SequenceExample {

  var seq:ListBuffer[Int] = ListBuffer(10)

  seq += 30

  seq.foreach((element:Int) => print(element+","))

  seq.drop(1)

}