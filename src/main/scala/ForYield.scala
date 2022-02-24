import scala.concurrent.duration.{DurationInt, FiniteDuration}
import scala.concurrent.{Await, ExecutionContext, Future, blocking}
import scala.util.{Failure, Success, Try}
import scala.concurrent.duration.Duration

/*
You construct futures to run tasks off of the main thread
Futures are intended for one-shot, potentially long-running concurrent tasks that eventually return a value
A future starts running as soon as you construct it
A benefit of futures over threads is that they come with a variety of callback methods that simplify the process of working with concurrent threads, including the handling of exceptions and thread management
Handle the result of a future with methods like onComplete, or combinator methods like map, flatMap, filter, andThen, etc.
The value in a Future is always an instance of one of the Try types: Success or Failure
If you’re using multiple futures to yield a single result, you’ll often want to combine them in a for-expression
 */

object ForYield extends App {

  def getNormal(id: String, sleepTime: Int): String = {
    log("Inside get " + id)
    Thread.sleep(sleepTime * 1000)
    log("Returning get " + id)
    id
  }

  def log(mesg: String): Unit =
    println(Thread.currentThread().getName + " - " + mesg)

  import scala.concurrent.ExecutionContext.Implicits.global

  def getFuture(id: String, sleepTime: Int): Future[String] = Future {
    log("Inside get " + id)
    blocking {
      Thread.sleep(sleepTime * 1000)
      log("Returning get " + id)
      id
    }
  }

  def getNoFuture(id: String, sleepTime: Int): String = {
    log("Inside get " + id)
    blocking {
      Thread.sleep(sleepTime * 1000)
      log("Returning get " + id)
      id
    }
  }


  def testBasicForComprehension() {
    log("-------- Testing Normal for comprehension ----------")
    for {
      a <- getNormal("A", 3)
      b <- getNormal("B", 1)
    } yield {
      log("A -> Got " + a)
      log("B -> Got " + b)
    }
  }

  log("-------- Testing Future for comprehension ----------")

  val startTime = System.currentTimeMillis()

  // Check-1. Incorrect. Futures run one after another
  def testIncorrectFutures() = {
      val result: Future[(String, String, String)] = for {
        a <- getFuture("FutA", 3)
        b <- getFuture("FutB", 2)
        c <- getFuture("FutC", 4)
      } yield (a, b, c)
  }

  // Check-2. Correct. Futures run immediately
  def testCorrectFutures() = {
    val f1 = getFuture("FutA", 3)
    val f2 = getFuture("FutB", 2)
    val f3 = getFuture("FutC", 4)
    (f1, f2, f3)
  }


  // Check-3. No Futures runs one after the other
  def testNoFutures() = {
    val f1 = getNoFuture("FutA", 3)
    val f2 = getNoFuture("FutB", 2)
    val f3 = getNoFuture("FutC", 4)
  }



  def properFutureUsage(): Future[String] = {
      val result: Future[(String, String, String)] = for {
        a <- f1
        b <- f2
        c <- f3
      } yield (a, b, c)

      val finalResult = result map { x =>
        log("Future item is: " + x._1)
        log("Got the result of futures: " + x + " in time " + (System.currentTimeMillis() - startTime) + " millis")
        x._1
      } recover {
        case e =>
          log("Some error happened")
          e.getMessage
      }

    finalResult
  }


  def multipleFuturesPutInSequence(): Future[String] = {
    val result = Future.sequence(List(f1, f2, f3))

    val finalResult:Future[String] = result map { x =>
      x map { y =>
        log("Future item is: " + y)
        Future.successful(y)
      }
      log("Got the result of futures: " + x + " in time " + (System.currentTimeMillis() - startTime) + " millis")
      "OK"
    } recover {
      case e =>
        log("Some error happened")
        e.getMessage
    }

    finalResult
  }

  //testBasicForComprehension()
  //testIncorrectFutures()
  val (f1, f2, f3) = testCorrectFutures()
  //testNoFutures()
  //val fut = properFutureUsage()



  val fut = multipleFuturesPutInSequence()

  import scala.language.postfixOps
  log("Waiting for the future to be complete.... Sleeping for 10 seconds")
  val x = System.currentTimeMillis()
  Await.result(fut, 10 seconds)

  log ("App Terminating")

}
