package com.jitterted.ebp.blackjack.domain;

import com.jitterted.ebp.blackjack.domain.port.GameMonitor;

public class Game {

  private final Deck deck;
  private final GameMonitor gameMonitor;

  private final Hand dealerHand = new Hand();
  private final Hand playerHand = new Hand();

  private boolean playerDone = false;
  private static final GameMonitor NULL_GAME_MONITOR = game -> {
  };

  public Game() {
    this(NULL_GAME_MONITOR);
  }

  public Game(Deck deck) {
    this(deck, NULL_GAME_MONITOR);
  }

  public Game(GameMonitor gameMonitor) {
    this(new Deck(), gameMonitor);
  }

  public Game(Deck deck, GameMonitor gameMonitor) {
    this.deck = deck;
    this.gameMonitor = gameMonitor;
  }

  public void initialDeal() {
    dealRoundOfCards();
    dealRoundOfCards();
  }

  private void dealRoundOfCards() {
    // why: players first because this is the rule
    playerHand.drawFrom(deck);
    dealerHand.drawFrom(deck);
  }

  public String determineOutcome() {
    if (playerHand.isBusted()) {
      return "You Busted, so you lose.  💸";
    } else if (playerHand.isBlackjack()) {
      return "You win Blackjack!";
    } else if (dealerHand.isBusted()) {
      return "Dealer went BUST, Player wins! Yay for you!! 💵";
    } else if (playerHand.beats(dealerHand)) {
      return "You beat the Dealer! 💵";
    } else if (playerHand.pushes(dealerHand)) {
      return "Push: The house wins, you Lose. 💸";
    } else {
      return "You lost to the Dealer. 💸";
    }
  }

  private void dealerTurn() {
    // Dealer makes its choice automatically based on a simple heuristic (<=16, hit, 17>stand)
    if (!playerHand.isBusted()) {
      while (dealerHand.dealerMustDrawCard()) {
        dealerHand.drawFrom(deck);
      }
    }
  }

  public Hand playerHand() {
    return playerHand;
  }

  public Hand dealerHand() {
    return dealerHand;
  }

  public void playerHits() {
    playerHand.drawFrom(deck);
    if (playerHand.isBusted()) {
      playerDone();
    }
  }

  public void playerStands() {
    dealerTurn();
    playerDone();
  }

  private void playerDone() {
    playerDone = true;
    gameMonitor.roundCompleted(this);
  }

  public boolean isPlayerDone() {
    return playerDone;
  }
}
