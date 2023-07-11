package example.poker

import example.poker.Domain.Hand

object PokerHands {
  sealed trait PokerHand

  object PokerHand {
    case object HighCard      extends PokerHand
    case object Pair          extends PokerHand
    case object TwoPair       extends PokerHand
    case object ThreeOfAKind  extends PokerHand
    case object Straight      extends PokerHand
    case object Flush         extends PokerHand
    case object FullHouse     extends PokerHand
    case object FourOfAKind   extends PokerHand
    case object StraightFlush extends PokerHand
  }

  def score(hand: Hand): Either[String, PokerHand] = 
    if(hand.cards.size == 5)
        // score the hand
        Right(PokerHand.HighCard)
    else
        Left("Not a scoreable hand")
}
