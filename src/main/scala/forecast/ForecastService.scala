package forecast

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest, Uri}
import akka.http.scaladsl.unmarshalling._
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport._

/**
 * A service that obtains a weather forecast
 */
class ForecastService(implicit actorSystem: ActorSystem) {
  implicit val materializer = ActorMaterializer()
  import materializer.executionContext
  import JsonProtocol._

  val appId = ConfigFactory.load().getString("openweathermap.appId")

  def fetchForecast(city: String) = {
    // Defining the request and sending it
    // See http://doc.akka.io/docs/akka-stream-and-http-experimental/current/java/http/client-side/request-level.html
    val uri = Uri("http://api.openweathermap.org/data/2.5/forecast")
      .withQuery("q" → city, "APPID" → appId)
    val request = HttpRequest(HttpMethods.GET, uri)
    val response = Http().singleRequest(request)

    // Unmarshalling the response when it’s ready
    // Read about Futures here: http://docs.scala-lang.org/overviews/core/futures.html
    // See http://doc.akka.io/docs/akka-stream-and-http-experimental/current/java/http/routing-dsl/marshalling.html
    response.flatMap(r ⇒ Unmarshal(r).to[Forecast])
  }
}
