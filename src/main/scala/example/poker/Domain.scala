package example.poker

object Domain {

  sealed trait Suit

  object Suit {
    case object Clubs    extends Suit
    case object Diamonds extends Suit
    case object Hearts   extends Suit
    case object Spades   extends Suit
  }

  sealed trait Rank

  object Rank {
    case object Two   extends Suit
    case object Three extends Suit
    case object Four  extends Suit
    case object Five  extends Suit
    case object Six   extends Suit
    case object Seven extends Suit
    case object Eight extends Suit
    case object Nine  extends Suit
    case object Ten   extends Suit
    case object Jack  extends Suit
    case object Queen extends Suit
    case object King  extends Suit
    case object Ace   extends Suit
  }

  final case class Card(rank: Rank, suit: Suit)

  final case class Deck()

  final case class Hand()

}
