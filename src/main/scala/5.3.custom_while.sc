

def myWhile(cond: => Boolean, body:  => Unit): Unit =
 {
  if (cond) {
    body
    myWhile(cond, body)
  }
}

def curryWhile(cond: => Boolean)(body: => Unit): Unit =
{
  if (cond) {
    body
    curryWhile(cond)(body)
  }
}

var i = 0
myWhile(i<30, {
  i = i+10
  println(i)
})

println("After control abstraction I is: " + i)
curryWhile(i<60) {
  i = i+10
  println(i)
}

println("After optimising with curry I is: " + i)
