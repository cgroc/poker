package example.poker

import munit.FunSuite
import example.poker.Domain._
import example.poker.Domain.Rank._
import example.poker.Domain.Suit._
import example.poker.PokerHands
import example.poker.PokerHands.PokerHand._

class PokerHandsSpec extends FunSuite {
  test("PokerHands should recognise a High Card") {
    val hand: Hand =
      Hand(
        List(
          Card(Queen, Diamonds),
          Card(Two, Clubs),
          Card(Three, Clubs),
          Card(Five, Spades),
          Card(Eight, Hearts)
        )
      )
    val score = PokerHands.score(hand)
    assertEquals(score, Right(HighCard))
  }

  test("PokerHands should recognise a Pair") {
    val hand: Hand =
      Hand(
        List(
          Card(Jack, Spades),
          Card(Jack, Hearts),
          Card(Ace, Clubs),
          Card(Three, Diamonds),
          Card(Seven, Spades)
        )
      )
    val score = PokerHands.score(hand)
    assertEquals(score, Right(Pair))
  }

  test("PokerHands should recognise Two Pair") {
    val hand: Hand =
      Hand(
        List(
          Card(Three, Hearts),
          Card(Three, Clubs),
          Card(Six, Clubs),
          Card(Six, Spades),
          Card(King, Diamonds)
        )
      )
    val score = PokerHands.score(hand)
    assertEquals(score, Right(TwoPair))
  }

  test("PokerHands should recognise Three Of A Kind") {
    val hand: Hand =
      Hand(
        List(
          Card(Five, Hearts),
          Card(Five, Diamonds),
          Card(Five, Clubs),
          Card(Jack, Spades),
          Card(Seven, Diamonds)
        )
      )
    val score = PokerHands.score(hand)
    assertEquals(score, Right(ThreeOfAKind))
  }

  test("PokerHands should recognise a Straight") {
    val hand: Hand =
      Hand(
        List(
          Card(Nine, Clubs),
          Card(Ten, Clubs),
          Card(Jack, Diamonds),
          Card(Queen, Spades),
          Card(King, Hearts)
        )
      )
    val score = PokerHands.score(hand)
    assertEquals(score, Right(Straight))
  }

  test("PokerHands should recognise an ace high Straight") {
    val hand: Hand =
      Hand(
        List(
          Card(Ten, Clubs),
          Card(Jack, Diamonds),
          Card(Queen, Spades),
          Card(King, Hearts),
          Card(Ace, Clubs)
        )
      )
    val score = PokerHands.score(hand)
    assertEquals(score, Right(Straight))
  }

  test("PokerHands should recognise an ace low Straight") {
    val hand: Hand =
      Hand(
        List(
          Card(Ace, Clubs),
          Card(Two, Clubs),
          Card(Three, Diamonds),
          Card(Four, Spades),
          Card(Five, Hearts)
        )
      )
    val score = PokerHands.score(hand)
    assertEquals(score, Right(Straight))
  }

  test("PokerHands should recognise a Flush") {
    val hand: Hand =
      Hand(
        List(
          Card(Three, Diamonds),
          Card(Six, Diamonds),
          Card(Seven, Diamonds),
          Card(Ten, Diamonds),
          Card(Ace, Diamonds)
        )
      )
    val score = PokerHands.score(hand)
    assertEquals(score, Right(Flush))
  }

  test("PokerHands should recognise a Full House") {
    val hand: Hand =
      Hand(
        List(
          Card(Six, Clubs),
          Card(Six, Diamonds),
          Card(Six, Hearts),
          Card(King, Spades),
          Card(King, Diamonds)
        )
      )
    val score = PokerHands.score(hand)
    assertEquals(score, Right(FullHouse))
  }

  test("PokerHands should recognise Four Of A Kind") {
    val hand: Hand =
      Hand(
        List(
          Card(Seven, Hearts),
          Card(Seven, Diamonds),
          Card(Seven, Clubs),
          Card(Seven, Spades),
          Card(Four, Clubs)
        )
      )
    val score = PokerHands.score(hand)
    assertEquals(score, Right(FourOfAKind))
  }

  test("PokerHands should recognise a Straight Flush") {
    val hand: Hand =
      Hand(
        List(
          Card(Two, Clubs),
          Card(Three, Clubs),
          Card(Four, Clubs),
          Card(Five, Clubs),
          Card(Six, Clubs)
        )
      )
    val score = PokerHands.score(hand)
    assertEquals(score, Right(StraightFlush))
  }

  test("PokerHands should reject a hand with too few cards") {
    val hand: Hand =
      Hand(
        List(
          Card(Two, Clubs),
          Card(Three, Clubs),
          Card(Four, Clubs),
          Card(Five, Clubs)
        )
      )
    val score = PokerHands.score(hand)
    assertEquals(score, Left("Not a scoreable hand"))
  }

  test("PokerHands should reject a hand with too many cards") {
    val hand: Hand =
      Hand(
        List(
          Card(Five, Hearts),
          Card(Five, Diamonds),
          Card(Five, Clubs),
          Card(Jack, Spades),
          Card(Seven, Diamonds),
          Card(Eight, Hearts)
        )
      )
    val score = PokerHands.score(hand)
    assertEquals(score, Left("Not a scoreable hand"))
  }
}
