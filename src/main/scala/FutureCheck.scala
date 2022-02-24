import java.text.SimpleDateFormat
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Try
import java.util.Date

object FutureCheck {

  var input = "Hello World"

  def doOp(): Future[String] =
    for {
      //result <- Future(deffered(input))
      result <- Future.fromTry(process("AAA"))

    } yield result

  def deffered(id: String): String = {
    info("deffered:Sleeping for 3 second")
    Thread.sleep(3000)
    info("deffered:Proceeding after sleep")
    id + "-deffered-DONE"
  }

  def process(id: String): Try[String] = {
    info("process:Sleeping for 3 second")
    Thread.sleep(3000)
    info("process:Proceeding after sleep")
    Try.apply(id + "-process-DONE")
  }

  import java.text.SimpleDateFormat

  val df = new SimpleDateFormat("HH:MM:SS")

  var ft = () => df.format(new Date())

  def info(mesg: String): Unit =
    println(ft.apply() + " " + Thread.currentThread.getName + " - " + mesg)

  def main(args: Array[String]): Unit = {
    info("Starting op:")
    var r = doOp()
    var x = 7
    while (x > 0) {
      x -= 1
      info(s"Counting down $x")
      Thread.sleep(1000)
    }
    r.map(value => info("Ending with value: " + value))
    info("Terminating op")
  }

}
