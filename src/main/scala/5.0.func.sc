import scala.math._

val num=3.14
val fun = ceil _

Array(10.3,33.9,23.5).map(fun).map((x: Double) => x * 2)
var myDouble = (x: Double) => x * 2
Array(10.3,33.9,23.5).map(fun).map(myDouble)

def valueAtOneQuarter(f: (Double) => (Double)) = f(1.25)

valueAtOneQuarter(ceil _)
valueAtOneQuarter(sqrt _)
valueAtOneQuarter(myDouble)

def mulBy(factor :Double) = (x: Double) =>  x * factor

valueAtOneQuarter( 8.75 + _)

(1 to 9).reduceLeft(_ * _) // ((( 1 * 2)) * 3) *4)

val triple = mulBy(3) // factor is a closure var
triple(5)

// currying - function takes 2 args would return a function with 1 arg
def multiply(x: Int) = (y: Int) => x * y
multiply(3)
multiply(3)(4)
//multiply(3)(4)(5)

// Control Abstractions
def runInThread (block: => Unit): Unit = {
  new Thread {
    override def run() { block }
  }.start()
}

runInThread { println("Hi");  println("Bye")}