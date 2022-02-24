object JunkScala {

  def getOrElse(thisOption: Option[Set[Integer]], thatOption: Set[String]): Option[Set[String]] =
    thisOption
      .map(_.map(_.toString))
      .orElse(
        Option(
          thatOption.zipWithIndex
            .filter { case (datum, index) => !(index == 0 && datum.equals("All")) }
            .map(_._1)
        )
      )

  var intSet: Option[Set[Integer]] = Option(Set(10, 30, 50))
  var strSet                       = Set("All")
  var strSet2                      = Set("Entry1")
  var strSet3                      = Set("Entry1", "Entry2")

  sealed trait ContactDirection
  case object INBOUND  extends ContactDirection
  case object OUTBOUND extends ContactDirection

  def main(args: Array[String]): Unit = {

    val check: ContactDirection = INBOUND

    println("CHECK: " + check.toString)

    println("Test1: " + getOrElse(intSet, strSet))
    println("Test2: " + getOrElse(None, strSet))
    println("Test3: " + getOrElse(None, strSet2))
    println("Test2: " + getOrElse(None, strSet3))

    println("----")
    val x = Map(10 -> "Ten", 20 -> "Twenty", 30 -> "Thirty")
    val y = Seq(10, 20)

    val yNotFound = y.filterNot(x.contains(_))

    println("Y not found" + yNotFound)

    println("IT IS: " + Array(100, 200, 300).mkString(","))

    def getKey(key: Int): String =
      if (key == 10) {
        return "Ten"
      } else {
        return null
      }

    Option(getKey(10)) match {
      case Some(value) => println("Got value: " + value)
      case _           => println("Not found")
    }
  }

}
