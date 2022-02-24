
// Adding a method - translate (behavior) to existing class.
import java.awt.geom._

trait RectangleLike {
  def setFrame(x:Double, y:Double, w: Double, h:Double)
  def getX:Double
  def getY:Double
  def getWidth: Double
  def getHeight: Double

  def translate(dx: Double, dy: Double) { setFrame(getX+dx, getY+dy, getWidth, getHeight)}
  override def toString = f"${getX},${getY}"
}
val egg = new Ellipse2D.Double( 5,10,20,30) with RectangleLike

egg.translate(10,20)
egg
