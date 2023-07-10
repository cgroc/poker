package example.poker

import example.poker.Domain._

trait Dealer[F[_]] {
  def shuffle(deck: Deck): F[Deck]
  def deal(deck: Deck): (Card, Deck)
}
