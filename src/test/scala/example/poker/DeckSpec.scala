package example.poker

import munit.FunSuite

class DeckSpec extends FunSuite {
  test("A Deck should contain 52 cards") {
    assertEquals(Domain.Deck.initialise.cards.size, 52)
  }
}
