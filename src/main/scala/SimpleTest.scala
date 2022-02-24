import java.time.Instant
import java.time.temporal.ChronoUnit._
object SimpleTest {
  def main(args: Array[String]): Unit = {
    val now = Instant.now()

    Thread.sleep(3000)

    println("Difference: " + SECONDS.between(now, Instant.now()))
    println("Difference: " + MINUTES.between(now, Instant.now()))
    println("Difference: " + MILLIS.between(now, Instant.now()))

    val payloadJson:String =
      "{\"WrapUpContactRequest\": {\"auxCodeId\":\"dummy-load-aux-code-id\", \"wrapUpReason\": \"dummy-load-reason\"}}"

    val startTimeMillisOfDay = 44570947
    val endTimeMillisOfDay = 13973190
    val contactOffsetTime = 44582206
    val timeCheck = (startTimeMillisOfDay <= contactOffsetTime
      && contactOffsetTime <= endTimeMillisOfDay)

    println("Time check: " + timeCheck)

    object EventOrder extends Enumeration {
      type EventOrder = Value
      // STORE_AND_STAT - store interaction id to event. Also process the event for stat
      // STORE_ONLY - store interaction id and event. This stat would not be logged
      val FIRST, MID, LAST = Value
    }

    import EventOrder._

    val check:EventOrder = LAST

    check match {
      case FIRST =>
        println("IT is first")
      case MID | LAST =>
        println("IT is mid and last")
      case LAST =>
        println("IT is last")
      case _ =>
        println("IT is something else: " + _)
    }

    val x = List(100L, "one hundred")

    println("List head is :" + x.head.asInstanceOf[Long])
    println("List tail is :" + x.last.asInstanceOf[String])

    var xx= java.util.UUID.randomUUID()

    var yy= xx.toString

    if (xx.toString.equals(yy)) {
      println("UUID equal")
    } else {
      println("UUID unequal")
    }



   // println(payloadJson)
  }
}
