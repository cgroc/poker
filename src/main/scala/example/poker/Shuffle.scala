package example.poker

import cats.implicits._
import cats.Functor
import cats.effect.std.Random
import example.poker.Domain._
import cats.effect.Sync

trait Shuffle[F[_]] {
  def shuffle(deck: Deck): F[Deck]
}

object Shuffle {
  def apply[F[_]: Functor](random: Random[F]): Shuffle[F] =
    new Shuffle[F] {
      def shuffle(deck: Deck): F[Deck] =
        random.shuffleList(deck.cards).map(Deck(_))
    }

  def create[F[_]: Sync]: F[Shuffle[F]] =
    Random.scalaUtilRandom.map(Shuffle(_))
}
