import scala.util.{ Failure, Success }

object ForTryv2 extends App {

  import scala.util.Try

  def logFailure[T](result: Try[T], desc: String): Unit =
    result match {
      case Failure(ex) => println(s"$desc is a failure with ${ex.getMessage()}", ex)
      case _           => ()
    }

  implicit class LogTryNew[T](res: Try[T]) {

    def withFailureLogging(desc: String): Try[T] = {
      logFailure(res, desc)
      res
    }
  }

  final case class CustomException(private val message: String = "", private val cause: Throwable = None.orNull)
      extends Exception(message, cause)

  def strGen(): String = "Hello World"
  //throw CustomException("Forced Error")

  def strPrinter(y: String): Try[Unit] = {
    println("Inside str printer")
    Try(println("The string is: " + y))
  }

//  Try { strGen() } match {
//    case Failure(exception: CustomException) => println("Got Failure of my exception: " + exception.getMessage)
//    case Success(y)                          => println("Something else")
//  }

  val G = "G"

  val res = Try(G match {
    case "G" =>
      strGen()
      true
    case _ => false
  }).getOrElse(false)

  println("Final result: " + res)

  val first: Try[Int]  = Try(throw CustomException("Failed 1"))
  val second: Try[Int] = Try(throw CustomException("Failed 2"))

  val min = for {
    f <- first orElse second
    s <- second orElse first
  } yield {
    Seq(f, s).min
  }
  println("Minimum is : " + min)

  println("Min equals 10 " + min.map(_.equals(10)).get)

}
