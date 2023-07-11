package example

import cats.effect.{ExitCode, IO, IOApp}
import example.poker.{Dealer, Shuffle}
import example.poker.Domain.Deck
import example.poker.PokerHands

object Main extends IOApp {
  def run(args: List[String]): cats.effect.IO[cats.effect.ExitCode] =
    (for {
      shuffle <- Shuffle.create[IO]
      deck   = Deck.initialise
      dealer = new Dealer(deck, shuffle)
      res <- dealer.dealHand
      hand = res._1
      _ <- IO.println(hand)
      score = PokerHands.score(hand)
      _ <- IO.println(s"You have a $score")
    } yield ()).as(ExitCode.Success)
}
