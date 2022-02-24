import java.util.UUID
import scala.util.{ Failure, Success, Try }
import java.lang.Throwable

object DeleteThisJunk extends App {

  case class AssociatedContact(
      isOffered: Boolean,
      mediaType: String,
      channelId: UUID
  )

  val a = AssociatedContact(true, "CHAT", UUID.randomUUID())
  val b = AssociatedContact(false, "VOICE", UUID.randomUUID())

  var list: scala.collection.mutable.Map[UUID, AssociatedContact] = scala.collection.mutable.Map()

  list += (UUID.randomUUID() -> a, UUID.randomUUID() -> b)

  println("List is: " + list)

  val tryList: Try[scala.collection.mutable.Map[UUID, AssociatedContact]] =
//    Failure(
//      new Exception("HERW")
//    )
    Success(
      list
    )

  println(
    "Format [Channel Assigned Status, MediaType, ChannelId] : " + tryList.fold(err => err.getMessage,
                                                                               m => m.mkString(","))
  )

}
