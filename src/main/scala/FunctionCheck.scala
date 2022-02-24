object FunctionCheck {

  def myFunc(fn: Integer => Unit): Unit =
    1.to(10).foreach(number => fn.apply(number))

  def call1(value: Integer): Unit =
    println("This is call1 with value: " + value)

  def call2(value: Integer): Unit =
    println("This is call2 with value: " + value)

  def checkFn(values: Int, msg: String): Unit =
    checkFn(List(values), msg)

  def checkFn(values: List[Int], msg: String): Unit =
    println(msg + " Got the values: " + values)

  def main(args: Array[String]): Unit =
    checkFn(List(10, 20, 30, 40), "Hello World")
  checkFn(50, "Hello World check")
  myFunc { i =>
    () -> {
      call1(i)
      call2(i)
    }
  }
}
