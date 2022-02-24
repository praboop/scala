import com.softwaremill.sttp._
import com.softwaremill.sttp.asynchttpclient.future.AsyncHttpClientFutureBackend

object AsyncHttpTest {


  def main(args: Array[String]): Unit = {
    val sort: Option[String] = None
    val query = "http language:scala"


    implicit val backend = AsyncHttpClientFutureBackend()
    implicit val ec = scala.concurrent.ExecutionContext.Implicits.global

    val request = sttp.get(
      uri"http://0.0.0.0:34")

    println("Making request....")
    val response = request
      .send()
      .map(resp => {
        println(resp.code) // 200
        println(resp.contentLength) // None
        println(resp.body) // // Right({"total_count":3041,"incomplete_results":false,"items":[{...}, ...]
      })
    println("After Making returned....")

    val fut = for {
      _ <- response
    } yield {
      ()
    }
    fut.onComplete(_ => backend.close())

  }



}
