# Poker

This repo contains the beginnings of an implementation for a poker simulation, written
for a technical challenge. It is set up as an `sbt` project and is
written in Scala 2.13. I chose 2.13 because I am more familiar with
Scala 2, but with hindsight perhaps it would have been nice to take
advantage of some of Scala 3's featues.

So far the simulation will model a deck of cards, shuffle the deck, deal a five hand
card and then categorise that hand according to the standard Poker scoring scheme.

To run the simulation:

```
    $ sbt run
```

To run tests:

```
    $ sbt test
```

## Overview

Everything except the `Main` file lives in `src/main/scala/poker`.

`Domain.scala` contains the basic domain model. 

Initially I wanted to have a `Set` of cards backing both the `Deck` and `Hand` types. It seems to me like in the context of a Poker game this would be a better model (although, if you wanted a deck for a game of Uno, maybe not...). However it seemed to me like this would complicate shuffling, as we would need a sorted set and the ability to apply arbitrary orderings. It seemed like this might be a time sink and I wanted to make progress.

`Shuffle.scala` provides an interface for shuffling a deck of cards. As this requires randomisation of the order I have made this effectful.

`Dealer.scala` is a class which takes a `Deck` and an implementation of `Shuffle`, shuffles the deck and then deals a hand of 5 cards. A couple of things to note:

+ The return type of the `dealHand` method is `F[(Hand, Deck)]`. My thinking here was that the game state would be managed outside of the `Dealer` class itself, so the remainder of the deck is returned alongside the hand.

+ This class isn't in its final form... it mixes up shuffling and dealing, it really shouldn't do that. I would be looking to refactor this so that the two things are handled separately. I feel like this would make more sense in the context of a full poker game model, but I haven't gotten that far :D

`PokerHands.scala` contains the model for types of poker hand (High Card, Full House etc) and also contains the functions for 'scoring' a hand (i.e. determining its type).

I would have liked to spend some time cleaning this file up a bit - possibly using some kind of refinement of types.

The logic around scoring hands rests on the idea that you can group cards by rank and then decide which hands are possible depending on the number of groups.

## Testing

Only very basic tests have been provided, it would be nice to use property based testing to generate different hands to test the scoring.
