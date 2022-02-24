import java.util.Scanner

object wordcount {

  val in= new Scanner((new java.net.URL("https://github.com/YourSimo/BookCayHorstmann/blob/master/src/com/horstmann/bigjava/ch15/spellcheck/alice30.txt").openStream))
  val mutableMap = scala.collection.mutable.Map[String, Int]()
  var immutableMap = Map[String, Int]()


  while(in.hasNext) {
    val word = in.next()
    mutableMap(word) = mutableMap.getOrElse(word, 0) + 1
    immutableMap = immutableMap + ( word -> (immutableMap.getOrElse(word, 0) + 1 ))

  }
  mutableMap("Alice")
  mutableMap("down")

  immutableMap("Alice")
  immutableMap("down")

}