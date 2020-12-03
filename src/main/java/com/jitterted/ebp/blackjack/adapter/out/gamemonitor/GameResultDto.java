package com.jitterted.ebp.blackjack.adapter.out.gamemonitor;

import com.jitterted.ebp.blackjack.domain.Game;

public class GameResultDto {

  private String playerName;
  private String outcome;
  private String playerHandValue;
  private String dealerHandValue;

  public static GameResultDto from(Game game) {
    GameResultDto gameResultDto = new GameResultDto();

    gameResultDto.playerName = "Ted";
    gameResultDto.outcome = game.determineOutcome();
    gameResultDto.dealerHandValue = game.dealerHand().displayValue();
    gameResultDto.playerHandValue = game.playerHand().displayValue();

    return gameResultDto;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public String getOutcome() {
    return outcome;
  }

  public void setOutcome(String outcome) {
    this.outcome = outcome;
  }

  public String getPlayerHandValue() {
    return playerHandValue;
  }

  public void setPlayerHandValue(String playerHandValue) {
    this.playerHandValue = playerHandValue;
  }

  public String getDealerHandValue() {
    return dealerHandValue;
  }

  public void setDealerHandValue(String dealerHandValue) {
    this.dealerHandValue = dealerHandValue;
  }
}
