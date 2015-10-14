package forecast

import akka.actor.ActorSystem

/** The main executable */
object Main extends App {
  implicit val actorSystem = ActorSystem()

  val soundService = new SoundService
  soundService.bind()
}
