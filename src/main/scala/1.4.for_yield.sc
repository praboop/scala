import scala.concurrent.duration.{ DurationInt, FiniteDuration }
import scala.concurrent.{ blocking, Await, ExecutionContext, Future }
import scala.util.{ Failure, Success }

class ForYield extends App {

  def getNormal(id: String, sleepTime: Int): String = {
    println("Inside Normal get " + id)
    Thread.sleep(sleepTime * 1000)
    println("Returning Normal get " + id)
    id
  }

  import scala.concurrent.ExecutionContext.Implicits.global

  def getFuture(id: String, sleepTime: Int): Future[String] = {
    println("Inside Future get " + id)
    Future {
      Thread.sleep(sleepTime * 1000)
      println("Returning Future get " + id)
      id
    }
  }

  println("Starting NORMAL Test")

  for {
    a <- getNormal("A", 3)
    b <- getNormal("B", 1)
  } yield {
    println("A -> Got " + a)
    println("B -> Got " + b)
  }

  val HttpTimeout: FiniteDuration = 6 seconds

  println("Starting FUTURE Test")

  val result: Future[(String, String)] = for {
    a <- getFuture("FutA", 3)
    b <- getFuture("FutB", 1)
  } yield (a, b)

  result.onComplete {
    case Success(x) => {
      println("Got the result of futures: " + x)
    }
    case Failure(e) => {
      e.printStackTrace
    }
  }

  Thread.sleep(8000)
}
