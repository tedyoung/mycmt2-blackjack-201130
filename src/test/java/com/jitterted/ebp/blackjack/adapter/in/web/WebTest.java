package com.jitterted.ebp.blackjack.adapter.in.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class WebTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getOfHomePageIsStatus200() throws Exception {
    mockMvc.perform(get("/index.html"))
           .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void postToStartGamePageRedirectsToGameView() throws Exception {
    mockMvc.perform(post("/start-game"))
           .andExpect(status().is3xxRedirection())
           .andExpect(redirectedUrl("/game"));
  }

  @Test
  public void getGamePageIs200() throws Exception {
    mockMvc.perform(get("/game"))
           .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void postToHitRedirectToGameView() throws Exception {
    mockMvc.perform(post("/hit"))
           .andExpect(status().is3xxRedirection())
           .andExpect(redirectedUrl("/game"));
  }

  @Test
  public void getDonePageIs200() throws Exception {
    mockMvc.perform(get("/done"))
           .andExpect(status().is2xxSuccessful());
  }
}
