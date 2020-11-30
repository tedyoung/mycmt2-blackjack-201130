package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardDisplayTest {

  private static final Suit DUMMY_SUIT = Suit.CLUBS;

  @Test
  public void displayTenAsString() throws Exception {
    Card card = new Card(DUMMY_SUIT, Rank.TEN);

    assertThat(card.display())
        .isEqualTo("\u001B[30m┌─────────┐\u001B[1B\u001B[11D│10       │\u001B[1B\u001B[11D│         │\u001B[1B\u001B[11D│    ♣    │\u001B[1B\u001B[11D│         │\u001B[1B\u001B[11D│       10│\u001B[1B\u001B[11D└─────────┘");
  }

  @Test
  public void displayNonTenAsString() throws Exception {
    Card card = new Card(DUMMY_SUIT, Rank.EIGHT);

    assertThat(card.display())
        .isEqualTo("\u001B[30m┌─────────┐\u001B[1B\u001B[11D│8        │\u001B[1B\u001B[11D│         │\u001B[1B\u001B[11D│    ♣    │\u001B[1B\u001B[11D│         │\u001B[1B\u001B[11D│        8│\u001B[1B\u001B[11D└─────────┘");
  }
}
