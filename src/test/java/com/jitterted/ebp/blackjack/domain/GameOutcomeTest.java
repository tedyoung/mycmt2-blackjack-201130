package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class GameOutcomeTest {

  @Test
  public void playerBeatsDealerThenOutcomeIsPlayerWins() throws Exception {
    Deck deck = new StubDeck();
    Game game = new Game(deck);
    game.initialDeal();
    game.playerStands();
    game.dealerTurn();

    // then outcome
    assertThat(game.determineOutcome())
        .isEqualTo("You beat the Dealer! 💵");
  }

  static class StubDeck extends Deck {
    private List<Card> cards = List.of(new Card(Suit.HEARTS, Rank.QUEEN), // dealt to the player
                                       new Card(Suit.SPADES, Rank.JACK), // dealer
                                       new Card(Suit.DIAMONDS, Rank.NINE), // player
                                       new Card(Suit.CLUBS, Rank.SEVEN)); // dealer
    private Iterator<Card> cardIterator = cards.listIterator();
    @Override
    public Card draw() {
      return cardIterator.next();
    }
  }
}
