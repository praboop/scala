object NumberToWord extends App {

/*
  val numMap:Map[Long, String] = Map(1000000000000L -> "trillion",
    1000000000L -> "billion",
    1000000L -> "million",
    1000L -> "thousand",
    100L -> "hundred")

  val tensMap: Map[Int, String] = Map(2 -> "twenty", 3 -> "thirty", 5 -> "fifty")

  val higherFunc = (y:Long) => numMap.find(_._1 <= y )

  val tensFunc = (y: Int) => tensMap.find(_._1 <= y)

  val combinerFunc = (y:Long) => higherFunc.apply(y).orElse(  (y>=20) => { 10 }  )
    //.getOrElse(y >= 20)


  println("Reduce returns: " + combinerFunc(21))

  //def matcher(num) = numMap.forEach((n,s) => num >= n)

  def speak(num: Int, printZero: Boolean = true): String = {
    if (num < 0) s"negative ${speak(-num)}"
    else if (num >= 1000000000) s"${speak(num / 1000000000)} billion ${speak(num % 1000000000, false)}"
    else if (num >= 1000000) s"${speak(num / 1000000)} million ${speak(num % 1000000, false)}"
    else if (num >= 1000) s"${speak(num / 1000)} thousand ${speak(num % 1000, false)}"
    else if (num >= 100) s"${speak(num / 100)} hundred ${speak(num % 100, false)}"
    else if (num >= 20) (num/10) match {
      case 2 => s"twenty ${speak(num % 10, false)}"
      case 3 => s"thirty ${speak(num % 10, false)}"
      case 5 => s"fifty ${speak(num % 10, false)}"
      case n@_ => s"${speak(n).stripSuffix("t")}ty ${speak(num % 10, false)}"
    }
    else num match {
      case 0 => if (printZero) "zero" else ""
      case 1 => "one"
      case 2 => "two"
      case 3 => "three"
      case 4 => "four"
      case 5 => "five"
      case 6 => "six"
      case 7 => "seven"
      case 8 => "eight"
      case 9 => "nine"
      case 10 => "ten"
      case 11 => "eleven"
      case 12 => "twelve"
      case 13 => "thirteen"
      case 15 =>"fifteen";
      case n@_ => s"${speak(n-10).stripSuffix("t")}teen"
    }
  }

  println(speak(5160, true))

 */
}
