package scalaexercises.stdlib.objects

class Movie(name: String, year: Int)

object Movie {

  def getBestPictureByYear(year: Int) {
    year match {
      case 2017 => Some(new Movie("Moonlight", year))
      case 2016 => Some(new Movie("Spotlight", year))
      case 2015 => Some(new Movie("Birdman", year))
      case _ => None
    }
  }

}
