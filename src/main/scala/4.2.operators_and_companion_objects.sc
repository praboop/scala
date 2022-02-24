

class Time(val h: Int, val m: Int) {


  // auxillary constructor - only hours
  // or add defaults in class def
  def this(h: Int) { this(h, 0) }

  var minutesInHour = h

  def hours = h

  val con1:Boolean = 1 to 23 contains h
  val con2:Boolean = 0 to 59 contains m

  if (!con1 || !con2) throw new IllegalArgumentException("Illegal argument")

  val _inMin: Integer = h * 60 + m

  def before(other: Time): Boolean = other._inMin > _inMin

  def < (other: Time): Boolean = before(other)

  def hours_= (newValue: Int) { minutesInHour = h }

  def - (other: Time) = h * 60 + m - other.h*60 - other.m

  override def toString = f"(${h}:${m})"
}

// Companion objects are like factory methods
object Time {
  def apply( hour: Int,  minute: Int) = new Time(hour, minute)
}

val morning =  Time(11, 30)
val afternoon =  Time( 12, 20)
val evening =  Time( 16, 0)
afternoon < morning
morning < evening
evening.before(morning)

println("Difference: " + (afternoon - morning))
evening.hours = 13
evening.toString

val invalid = new Time(25, 20)






