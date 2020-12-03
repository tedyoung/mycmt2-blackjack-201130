package com.jitterted.ebp.blackjack;

import com.jitterted.ebp.blackjack.adapter.out.gamemonitor.HttpGameMonitor;
import com.jitterted.ebp.blackjack.domain.Game;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlackjackGameApplication {

  public static void main(String[] args) {
//		ConsoleGame.main(new String[]{});
    SpringApplication.run(BlackjackGameApplication.class, args);
  }

  @Bean
  public Game createGame() {
    return new Game(new HttpGameMonitor());
  }

}
