import slick.jdbc.H2Profile.api._

import java.time.LocalDate
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ Await, Future }
import scala.concurrent.duration.{ DurationInt, FiniteDuration }
import scala.language.postfixOps

import scala.concurrent.{ blocking, Await, ExecutionContext, Future }

object H2DBTest extends App {
  val db = Database.forConfig("h2mem")

  case class Player(id: Long, name: String, country: String, dob: Option[LocalDate], skillId: Option[String])

  class PlayerTable(tag: Tag) extends Table[Player](tag, None, "Player") {
    override def *                   = (id, name, country, dob, skillId) <> (Player.tupled, Player.unapply)
    val id: Rep[Long]                = column[Long]("PlayerId", O.AutoInc, O.PrimaryKey)
    val name: Rep[String]            = column[String]("Name")
    val country: Rep[String]         = column[String]("Country")
    val dob: Rep[Option[LocalDate]]  = column[Option[LocalDate]]("Dob")
    val skillId: Rep[Option[String]] = column[Option[String]]("Skill")
  }

  val playerTable = TableQuery[PlayerTable]

  println("Creating schema")

  val createSchema = db.run(
    DBIO.seq(
      playerTable.schema.create
    )
  )
  Await.result(createSchema, 5 seconds)
  println("created schema")

  val checkSkill1: Option[String] = Some("GERMAN-SKILL")
  val checkSkill2: Option[String] = Some("BLR-SKILL")
  val noneSkill: Option[String]   = None

  println("Inserting three records \n")
  val player1 = Player(1, "Joe1", "Germany", None, checkSkill1)
  val player2 = Player(2, "Joe2", "BLR", None, checkSkill2)
  val player3 = Player(2, "Joe3", "somecountry", None, noneSkill)

  val insertPlayer1Query         = playerTable += player1
  val insertResult1: Future[Int] = db.run(insertPlayer1Query)
  Await.result(insertResult1, 5 seconds)

  val insertPlayer2Query         = playerTable += player2
  val insertResult2: Future[Int] = db.run(insertPlayer2Query)
  Await.result(insertResult2, 5 seconds)

  val insertPlayer3Query         = playerTable += player3
  val insertResult3: Future[Int] = db.run(insertPlayer3Query)
  Await.result(insertResult3, 5 seconds)
  println("Inserted three records in H2DB\n")

  println("fetch only German record from H2DB\n")
  val germanPlayersQuery                 = playerTable.filter(_.skillId === checkSkill1)
  val germanPlayers: Future[Seq[Player]] = db.run[Seq[Player]](germanPlayersQuery.result)
  Await.result(germanPlayers, 5 seconds)
  germanPlayers.onComplete { german =>
    System.out.println("German row: " + german)
  }

  println("fetch only Bangalore record from H2DB\n")
  val blrPlayersQuery                 = playerTable.filter(_.skillId === checkSkill2)
  val blrPlayers: Future[Seq[Player]] = db.run[Seq[Player]](blrPlayersQuery.result)
  Await.result(blrPlayers, 5 seconds)
  blrPlayers.onComplete { blr =>
    System.out.println("BLR row: " + blr)
  }

  println("fetch only Somecountry record from H2DB\n")
  //val somecountryPlayersQuery                 = playerTable.filterNot(_.skillId === noneSkill)
  val somecountryPlayersQuery = playerTable.filterNot(_.skillId.isDefined)

  val somecountryPlayers: Future[Seq[Player]] = db.run[Seq[Player]](somecountryPlayersQuery.result)
  Await.result(somecountryPlayers, 5 seconds)
  somecountryPlayers.onComplete { somecountry =>
    System.out.println("Somecountry row: " + somecountry)
  }

  println("fetch all records except German record from H2DB\n")

  //val exceptGermanQuery                        = playerTable.filterNot(_.skillId.isDefined).filterNot(_.skillId === checkSkill1)
  //val exceptGermanQuery = playerTable.filterNot(x => x.skillId.isDefined && (x.skillId === checkSkill1 ) )
  val exceptGermanQuery =
    playerTable.filter(x => (x.skillId.isEmpty || x.skillId =!= checkSkill1))

  val exceptGermanPlayers: Future[Seq[Player]] = db.run[Seq[Player]](exceptGermanQuery.result)
  Await.result(exceptGermanPlayers, 5 seconds)
  exceptGermanPlayers.onComplete { exceptGerman =>
    System.out.println("All rows except German: " + exceptGerman)
  }

  // select teamid,agentid from Agent where teamId=val.teamId
  // select teamid,agentid from Agent where teamId=val.teamId and skillProfileId != val.skillProfileId

  //None != blaw
  //blaw !- None

}
