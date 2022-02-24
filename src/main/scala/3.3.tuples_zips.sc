import scala.collection.mutable.ArrayBuffer

"New York".partition(_.isUpper)

var symbols = Array("one", "two")
var numeric = Array ("1", "2")

var pairs = symbols.zip(numeric)


// collect only positive except first negative
var arr = ArrayBuffer(4,32,2,-4,22,4,-5,-5,33,-9,0)

val (neg, pos) = arr.partition(_ < 0)
var result =pos
result += neg(0)


