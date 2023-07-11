package example.poker

import cats.Show
import cats.implicits._
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

  sealed trait Rank extends EnumEntry {
    val value: Int
  }

  object Rank extends Enum[Rank] {

    val values = findValues

    case object Two   extends Rank { val value = 1  }
    case object Three extends Rank { val value = 2  }
    case object Four  extends Rank { val value = 3  }
    case object Five  extends Rank { val value = 4  }
    case object Six   extends Rank { val value = 5  }
    case object Seven extends Rank { val value = 6  }
    case object Eight extends Rank { val value = 7  }
    case object Nine  extends Rank { val value = 8  }
    case object Ten   extends Rank { val value = 9  }
    case object Jack  extends Rank { val value = 10 }
    case object Queen extends Rank { val value = 11 }
    case object King  extends Rank { val value = 12 }
    case object Ace   extends Rank { val value = 13 }
  }

  final case class Card(rank: Rank, suit: Suit)

  object Card {
    implicit val showCard: Show[Card] =
      card => s"${card.rank} of ${card.suit}"
  }

  final case class Deck(cards: List[Card])

  object Deck {
    def initialise: Deck =
      Deck(
        for {
          rank <- Rank.values.toList
          suit <- Suit.values.toList
        } yield Card(rank, suit)
      )
  }

  final case class Hand(cards: List[Card])

  object Hand {
    implicit val showHand: Show[Hand] =
      hand =>
        s"""Hand:
           |  ${hand.cards
            .map(card => card.show + "\n  ")
            .combineAll}""".stripMargin
  }

}
