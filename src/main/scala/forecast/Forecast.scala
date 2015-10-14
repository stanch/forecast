package forecast

import com.softwaremill.quicklens._

/*
 * Here we define our data model.
 * Wondering why these are called case classes?
 * Read this: https://www.artima.com/pins1ed/case-classes-and-pattern-matching.html
 */

case class City(name: String, country: String)

case class WindData(speed: Float, direction: Float)

case class ForecastData(
  temperature: Float,
  pressure: Float,
  humidity: Float,
  clouds: Float,
  wind: WindData
) {
  // Showcasing quicklens for updates to deep immutable structures
  // See https://github.com/adamw/quicklens
  def windier = this.modify(_.wind.speed).using(_ + 10)
}

case class Forecast(city: City, list: List[ForecastData])
