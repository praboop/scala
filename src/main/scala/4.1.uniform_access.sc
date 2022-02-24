

class Time( h: Int,  m: Int) {


  // auxillary constructor - only hours
  // or add defaults in class def
  def this(h: Int) { this(h, 0) }

  var minutesInHour = h

  def hours = h

  val con1:Boolean = 1 to 23 contains h
  val con2:Boolean = 0 to 59 contains m

  if (!con1 || !con2) throw new IllegalArgumentException("Illegal argument")

  val _inMin: Integer = h * 60 + m

  def before(other: Time): Boolean = other._inMin < _inMin

  def hours_= (newValue: Int) { minutesInHour = h }

  override def toString = f"(${h}:${m})"
}

val morning = new Time(10, 30)
val afternoon = new Time( 14, 20)
val noon = new Time( 12, 0)
afternoon.before(morning)
noon.before(morning)
noon.hours = 13
noon.toString

val invalid = new Time(25, 20)






