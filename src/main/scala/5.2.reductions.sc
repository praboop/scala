(1 to 10).reduceLeft(_ * _)

def fact(value: Int): Int = (1 to value).reduceLeft( _ * _)

var x = fact(3)

def concat(strings: Seq[String], separator: String)
  = strings.reduceLeft(_ + separator + _)

concat(Array("Mary", "Had", "A", "little", "lamb"), " ")