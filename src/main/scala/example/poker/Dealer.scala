package example.poker

import example.poker.Domain._
import cats.Functor
import cats.implicits._

class Dealer[F[_]: Functor](deck: Deck, shuffle: Shuffle[F]) {
  def dealHand: F[(Hand, Deck)] =
    shuffle.shuffle(deck).map { shuffled =>
      (Hand(shuffled.cards.take(5)), Deck(shuffled.cards.drop(5)))
    }
}
