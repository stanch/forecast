package forecast

import play.api.libs.json.{Format, JsPath, Json}
import play.api.libs.functional.syntax._

/**
 * This JSON protocol defines how we want to map out data to JSON.
 * See https://www.playframework.com/documentation/2.4.x/ScalaJson.
 */
object JsonProtocol {
  // This format is automatically derived
  implicit val cityFormat = Json.format[City]

  // Here we want more flexibility to rename the fields
  implicit val windData: Format[WindData] = (
    (JsPath \ "speed").format[Float] and
    (JsPath \ "deg").format[Float]
  )((s, d) ⇒ WindData(s, d), data ⇒ (data.speed, data.direction))

  // This one is even more flexible, since it does not map directly
  // to the fields in JSON
  implicit val forecastDataFormat = (
    (JsPath \ "main" \ "temp").format[Float] and
    (JsPath \ "main" \ "pressure").format[Float] and
    (JsPath \ "main" \ "humidity").format[Float] and
    (JsPath \ "clouds" \ "all").format[Float] and
    (JsPath \ "wind").format[WindData]
  )(ForecastData.apply, unlift(ForecastData.unapply))

  // This format is again derived automatically
  implicit val forecastFormat = Json.format[Forecast]
}
