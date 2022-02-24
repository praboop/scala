import scala.util.{Failure, Success, Try}


def throwFunc(): Try[String] = {
    throw new Exception("This is ERROR")
}

def nonThrowFunc(): Try[String] = {
  Success("Hello World")
}

var x = Try {throwFunc()}
//var x = Try {nonThrowFunc()}


def handle(): Unit = {

  println("Inside handle")

  x match  {
    case Success(_) => println("SUCCESS => success")
    case Failure(exc) => println(s"FAILURE => caught: $exc")
    case _ => println("UNKNOWN")
      println("IMPORTANT METHOD")
  }

}

handle()

