package forecast

import org.scalatest.prop.PropertyChecks
import org.scalatest.{Matchers, FlatSpec}
import org.scalacheck.Shapeless._
import JsonProtocol._
import play.api.libs.json.{JsSuccess, Json}

class ForecastSpec extends FlatSpec with Matchers with PropertyChecks {
  behavior of "ForecastData"

  /*
   * These tests use automatically generated random data.
   * See:
   *   * http://www.scalatest.org/user_guide/generator_driven_property_checks
   *   * https://www.scalacheck.org/
   *   * https://github.com/alexarchambault/scalacheck-shapeless
   */

  it should "survive a roundtrip to and from JSON" in {
    forAll { data: ForecastData ⇒
      val json = Json.toJson(data)
      val backFromJson = json.validate[ForecastData]
      backFromJson shouldEqual JsSuccess(data)
    }
  }

  it should "become windier when asked to" in {
    forAll { data: ForecastData ⇒
      whenever(data.wind.speed > 0 && data.wind.speed < 1000) {
        data.windier.wind.speed should be > data.wind.speed
      }
    }
  }
}
