object gby {

  val words = Array("one", "two", "three", "four", "five", "six", "seven")

  // cluster buy first letter
  // returns key:value => key is first letter and value is array of matching strings.
  var x = words.groupBy(_.substring(0,1))

  // version 0
  for ((k,v) <- x) printf(" %s=%s\n", k, v.mkString(","))

  // version 1 (tuples)
  x foreach (x => println (" " + x._1 + "-->" + x._2.mkString(",")))

  // version 2 (foreach and case)
  x foreach {case (key, value) => println (key + "-->" + value.mkString(","))}

  // cluster by length
  words.groupBy(_.length).foreach(x => println(" " + x._1 + " = " + x._2.mkString((",")) + "\n"))
}