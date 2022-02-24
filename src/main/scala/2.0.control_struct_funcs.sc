import scala.math._

object Test {

  def isVowel(x: Char)  = "aeiou".contains(x)

  def getVowels(y: String) = {
    for (j <- y if isVowel(j)) yield j
  }

  getVowels("marcos")

  def squares(y: Int=6) = {
    for (j <- 1.to(y)) yield j*j
  }


  squares(4).toArray
  squares().toArray


}