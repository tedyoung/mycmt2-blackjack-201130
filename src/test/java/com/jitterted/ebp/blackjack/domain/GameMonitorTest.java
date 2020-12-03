package com.jitterted.ebp.blackjack.domain;

import com.jitterted.ebp.blackjack.domain.port.GameMonitor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class GameMonitorTest {

  @Test
  public void playerStandsCompletesGameSendsToMonitor() throws Exception {
    // creates the spy based on the interface
    GameMonitor gameMonitorSpy = spy(GameMonitor.class);

    Game game = new Game(gameMonitorSpy);
    game.initialDeal();

    game.playerStands();

    // verify that the roundCompleted method was called with any instance of a Game class
    verify(gameMonitorSpy).roundCompleted(any(Game.class));
  }

  @Test
  public void playerBustsGameSendsToMonitor() throws Exception {
    GameMonitor gameMonitorSpy = spy(GameMonitor.class);

    Deck deck = new StubDeck(List.of(new Card(Suit.HEARTS, Rank.QUEEN), // dealt to the player
                                     new Card(Suit.SPADES, Rank.JACK), // dealer
                                     new Card(Suit.DIAMONDS, Rank.NINE), // player
                                     new Card(Suit.CLUBS, Rank.SEVEN),
                                     new Card(Suit.DIAMONDS, Rank.JACK)));

    Game game = new Game(deck, gameMonitorSpy);
    game.initialDeal();
    game.playerHits();

    verify(gameMonitorSpy).roundCompleted(any(Game.class));
  }

}
