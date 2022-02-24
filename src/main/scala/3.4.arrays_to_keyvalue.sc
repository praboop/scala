import scala.collection.mutable.ArrayBuffer

val buffer = ArrayBuffer(1,3,5,4,6,7,8,1,15,14,11)

var result = buffer.map(x => (x, x%2))

println(result);

var contactNo = 4;

var piq =
  result.contains((contactNo, 0)) match {
  case true =>
    (contactNo, -1);
  case false =>
    result.reduce((a, b) => {
      if (a._2 == -1) {
        println("1 - returning " + (a._1, a._2))
        (a._1, a._2)
      } else {
        println("3 - returning " + (b._1, a._2 + b._2))
        (contactNo, a._2 + b._2)
      }
    })
}

piq