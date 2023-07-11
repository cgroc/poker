package example

import cats.effect.{ExitCode, IO, IOApp}
import example.poker.{Dealer, Shuffle}
import example.poker.Domain.Deck

object Main extends IOApp {
  def run(args: List[String]): cats.effect.IO[cats.effect.ExitCode] =
    (for {
      shuffle <- Shuffle.create[IO]
      deck   = Deck.initialise
      dealer = new Dealer(deck, shuffle)
      res <- dealer.dealHand
      _   <- IO.println(res._1)
    } yield ()).as(ExitCode.Success)
}
