package example.poker

import example.poker.Domain._

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
    if (hand.cards.size == 5) {
      // score the hand
      val groupedByRank: List[List[Card]] =
        hand.cards.groupBy(_.rank).values.toList
      groupedByRank.length match {
        case 5 => scoreFiveGroups(hand)
        case 4 => Left("Not Implemented")
        case 3 => Left("Not Implemented")
        case 2 => Left("Not Implemented")
        case _ =>
          Left(
            "This is an impossible state for poker hands, it would be nice to eliminate it"
          )
      }
    } else
      Left("Not a scoreable hand")

  // TODO: Edge cases around ace high/ace low won't be handled
  private def isStraight(hand: Hand): Boolean = {
    val sorted = hand.cards.map(_.rank.value).sorted
    isSequential(sorted)
  }

  // TODO: Refactor recursion?
  private def isSequential(l: List[Int]): Boolean =
    l match {
      case Nil      => true
      case _ :: Nil => true
      case i :: j :: rest =>
        if (j == i + 1)
          isSequential(j :: rest)
        else
          false
    }

  private def isFlush(hand: Hand): Boolean =
    hand.cards.groupBy(_.suit).size == 1

  private def scoreFiveGroups(hand: Hand): Either[String, PokerHand] =
    (isFlush(hand), isStraight(hand)) match {
      case (true, true)  => Right(PokerHand.StraightFlush)
      case (true, false) => Right(PokerHand.Flush)
      case (false, true) => Right(PokerHand.Straight)
      case _             => Right(PokerHand.HighCard)
    }
}
