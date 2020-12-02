package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class GameOutcomeTest {

  @Test
  public void playerBeatsDealerThenOutcomeIsPlayerWins() throws Exception {
    Deck deck = new StubDeck(List.of(new Card(Suit.HEARTS, Rank.QUEEN), // dealt to the player
                                     new Card(Suit.SPADES, Rank.JACK), // dealer
                                     new Card(Suit.DIAMONDS, Rank.NINE), // player
                                     new Card(Suit.CLUBS, Rank.SEVEN)));
    Game game = new Game(deck);
    game.initialDeal();
    game.playerStands();

    // then outcome
    assertThat(game.determineOutcome())
        .isEqualTo("You beat the Dealer! 💵");
  }

  @Test
  public void playerWithBlackjackImmediatelyWins() throws Exception {
    Deck deck = new StubDeck(List.of(new Card(Suit.HEARTS, Rank.JACK), // dealt to the player
                                     new Card(Suit.SPADES, Rank.JACK), // dealer
                                     new Card(Suit.DIAMONDS, Rank.ACE), // player
                                     new Card(Suit.CLUBS, Rank.SEVEN)));
    Game game = new Game(deck);

    game.initialDeal();
    game.playerStands();

    assertThat(game.determineOutcome())
        .isEqualTo("You win Blackjack!");
  }

  @Test
  public void playerStandsResultsInDealerTakingItsTurn() throws Exception {
    Deck deck = new StubDeck(List.of(new Card(Suit.HEARTS, Rank.JACK), // dealt to the player
                                     new Card(Suit.SPADES, Rank.JACK), // dealer
                                     new Card(Suit.DIAMONDS, Rank.QUEEN), // player
                                     new Card(Suit.CLUBS, Rank.SIX),
                                     new Card(Suit.SPADES, Rank.TWO)));
    Game game = new Game(deck);
    game.initialDeal();

    game.playerStands();

    assertThat(game.dealerHand().cards())
        .hasSize(3);
  }

  @Test
  public void dealerTakesTurnTwiceThrowsException() throws Exception {
    // exercise for the reader
  }

}
