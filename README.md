### Forecast

This is the demo from the talk I gave at the Lisbon Scala meetup on October 15, 2015.
If features a simple web server that respons with wind sounds according to a weather forecast.
The code is best viewed in the following order:

* [Forecast.scala](src/main/scala/forecast/Forecast.scala) — **Data model**

  This file shows how we can concisely model real-world data with Scala case classes.
  It also illustrates some tricks for “updating” immutable data.
* [ForecastSpec.scala](src/test/scala/forecast/ForecastSpec.scala) — **Property-based tests**

  Here you can see how you can use property-based tests with automatically derived generators
  to check properties on a random sample of your data.
* [JsonProtocol.scala](src/main/scala/forecast/JsonProtocol.scala) — **JSON protocol**

  This module showcases how we can automatically and manually provide mappings
  between our data and JSON.
* [ForecastService.scala](src/main/scala/forecast/ForecastService.scala) — **Making web requests**

  Exemplified here is the usage of Akka-Http client library.
* [SoundService.scala](src/main/scala/forecast/SoundService.scala) — **A simple web server**

  The final piece of this project combines routes and layouts implemented in Scala to
  assemble a simple web service that returns different wind sounds based on the speed of the wind.
* [build.sbt](build.sbt) — View the dependencies of this project.

#### Running

1. Install sbt: http://www.scala-sbt.org/release/tutorial/Setup.html
2. Run `sbt test` to run the tests.
3. Run `sbt run` to start the web server.

