package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
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
    game.dealerTurn();

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
    game.dealerTurn();

    assertThat(game.determineOutcome())
        .isEqualTo("You win Blackjack!");
  }

  static class StubDeck extends Deck {
    private Iterator<Card> cardIterator;

    public StubDeck(List<Card> cards) {
      this.cardIterator = cards.listIterator();
    }

    @Override
    public Card draw() {
      return cardIterator.next();
    }
  }
}
