trait Logged {
  def log(msg: String) {}
}

trait ConsoleLogger extends Logged {
  override def log(msg: String) { println(msg) }
}

class SavingsAccount extends Logged {
  private var balance: Double = 0

  def withdraw(amount: Double): Unit = {
    if (balance < amount) log("Insufficient funds")
    else balance -= amount
  }

  override def toString: String = s"Balance: ${balance}"
}

trait TimestampLogger extends Logged {
  override def log(msg: String): Unit = {
    super.log(new java.util.Date() + " " + msg)
  }
}

trait ShortLogger extends Logged {
  val maxLength = 15

  override def log(msg: String) = {
    super.log(
      if (msg.length <= maxLength) msg
      else msg.substring(0, maxLength -1) + "..."
    )
  }
}


var acct = new SavingsAccount
    with ConsoleLogger
    with TimestampLogger
    with ShortLogger {
      override val maxLength: Int = 10
    }
acct.withdraw(1000)

var acct1 = new SavingsAccount
  with ShortLogger
  with TimestampLogger
  with ConsoleLogger {
  override val maxLength: Int = 10
}
acct1.withdraw(1000)