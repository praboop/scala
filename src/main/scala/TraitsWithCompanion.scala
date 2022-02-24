import Simple.count

trait Simple {

}

object Simple {
  var count = 0


  def apply() = {
    count += 1
    print("Simple object created")
    new SimpleImpl()
  }

}

class SimpleImpl extends Simple {

  def printCount(): Unit = {
    System.out.println("Count is " + count)
  }
}

object TraitsWithCompanion {


  def main(args: Array[String]): Unit = {
    val one = Simple();
    one.printCount()
    val two =  Simple();
    two.printCount()
  }
}
