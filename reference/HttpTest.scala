//import sttp.client._
import com.softwaremill.sttp._
import scala.concurrent.Future
import com.softwaremill.sttp.asynchttpclient.future.AsyncHttpClientFutureBackend
import java.nio.ByteBuffer

import cats.effect.{ContextShift, IO}
import com.softwaremill.sttp.Response
import fs2.Stream

import scala.concurrent.duration.Duration

//import org.http4s.client.Client
//import sttp.client3.quick.backend


object HttpTest {


  //import sttp.client.http4s._

  //val client: Client[IO] = ???

  import scala.concurrent._

  def main(args: Array[String]): Unit = {
    val sort: Option[String] = None
    val query = "http language:scala"
   // implicit val backend = HttpURLConnectionBackend()
   //implicit val backend = Http4sBackend(client)

    implicit val backend = AsyncHttpClientFutureBackend()
    implicit val ec = scala.concurrent.ExecutionContext.Implicits.global
    //implicit val sttpBackend = AsyncHttpClientFutureBackend()


    // the `query` parameter is automatically url-encoded
    // `sort` is removed, as the value is not defined

 //   val request = basicRequest.get(uri"https://api.github.com/search/repositories?q=$query&sort=$sort")

    //val request = basicRequest.get(uri"http://0.0.0.0:34")

    //val backend = HttpURLConnectionBackend()

    //val response = request.send(backend)

   // val response = request.send()


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

    // response.header(...): Option[String]
   // println(response.header("Content-Length"))

    // response.body: by default read into an Either[String, String] to indicate failure or success
   // println(response.body)
  }

}
