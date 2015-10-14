package forecast

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._
import scalatags.Text.all._

/** The main web service */
class SoundService(implicit actorSystem: ActorSystem) {
  implicit val materializer = ActorMaterializer()
  import materializer.executionContext
  import ScalaTagsSupport._

  val forecastService = new ForecastService

  // A landing layout defined with ScalaTags
  // See http://lihaoyi.github.io/scalatags/
  val landingLayout = div(
    h1("Enter a city name to hear the wind sound!"),
    form(
      action := "play",
      input(`type` := "text", name := "city", placeholder := "City"),
      input(`type` := "submit", value := "Play!")
    )
  )

  // The landing endpoint
  // See http://doc.akka.io/docs/akka-stream-and-http-experimental/current/java/http/routing-dsl/overview.html
  val landingRoute = pathEndOrSingleSlash {
    complete(landingLayout)
  }

  // The wind strength endpoint
  val windRoute = path("play") {
    parameter("city") { city ⇒
      onSuccess(forecastService.fetchForecast(city)) { forecast ⇒
        val strength = if (forecast.list.exists(_.wind.speed > 5)) {
          "strong"
        } else {
          "normal"
        }
        getFromResource(s"wind-$strength.mp3")
      }
    }
  }

  // Combine the routes
  val routes = landingRoute ~ windRoute

  // Start serving
  def bind() = Http().bindAndHandle(routes, "0.0.0.0", 8080)
}
