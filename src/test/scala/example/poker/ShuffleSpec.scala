package example.poker

import cats.effect.IO
import munit.CatsEffectSuite
import example.poker.Domain._

class ShuffleSpec extends CatsEffectSuite {

  test("Shuffle should change the order of a deck of cards") {
    val deck    = Deck.initialise
    val shuffle = Shuffle.create[IO]
    val newDeck = shuffle.flatMap(_.shuffle(deck)).unsafeRunSync()
    assertNotEquals(newDeck.cards, deck.cards)
  }

}
