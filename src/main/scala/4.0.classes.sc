

class Time(val hours: Int, val minutes: Int) {

  // auxillary constructor - only hours
  // or add defaults in class def
  def this(h: Int) { this(h, 0) }

  val con1:Boolean = 1 to 23 contains hours
  val con2:Boolean = 0 to 59 contains minutes

  if (!con1 || !con2) throw new IllegalArgumentException("Illegal argument")

  val _inMin: Integer = hours * 60 + minutes

  def before(other: Time): Boolean = other._inMin < _inMin

  override def toString = f"(${hours}:${minutes})"
}

val morning = new Time(10, 30)
val afternoon = new Time( 14, 20)
val noon = new Time( 12, 00)
afternoon.before(morning)
noon.before(morning)

val invalid = new Time(25, 20)





