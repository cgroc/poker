package example.poker

import munit.FunSuite
import Domain._

class DeckSpec extends FunSuite {
  test("An initial deck should contain 52 cards") {
    assertEquals(Deck.initialise.cards.size, 52)
  }

  test("An initial deck should have 4 cards of each rank") {
    val initialDeck = Deck.initialise
    val rankGroups  = initialDeck.cards.groupBy(_.rank).map(_._2.size)
    assert(rankGroups.forall(_ == 4))
  }

  test("An initial deck should have 13 cards of each suit") {
    val initialDeck = Deck.initialise
    val rankGroups  = initialDeck.cards.groupBy(_.suit).map(_._2.size)
    assert(rankGroups.forall(_ == 13))
  }
}
