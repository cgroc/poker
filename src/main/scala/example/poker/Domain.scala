package example.poker

import enumeratum._

object Domain {

  sealed trait Suit extends EnumEntry

  object Suit extends Enum[Suit] {

    val values = findValues

    case object Clubs    extends Suit
    case object Diamonds extends Suit
    case object Hearts   extends Suit
    case object Spades   extends Suit
  }

  sealed trait Rank extends EnumEntry

  object Rank extends Enum[Rank] {

    val values = findValues

    case object Two   extends Rank
    case object Three extends Rank
    case object Four  extends Rank
    case object Five  extends Rank
    case object Six   extends Rank
    case object Seven extends Rank
    case object Eight extends Rank
    case object Nine  extends Rank
    case object Ten   extends Rank
    case object Jack  extends Rank
    case object Queen extends Rank
    case object King  extends Rank
    case object Ace   extends Rank
  }

  final case class Card(rank: Rank, suit: Suit)

  trait Deck {
    val cards: Set[Card]
    def deal: (Deck, Hand) = ???
  }

  object Deck {
    def initialise: Deck =
        new Deck {
            val cards = 
              for {
                rank: Rank <- Rank.values.toSet
                suit: Suit <- Suit.values.toSet
              } yield Card(rank, suit)
        }
  }

  final case class Hand()

}
