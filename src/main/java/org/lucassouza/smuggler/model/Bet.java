package org.lucassouza.smuggler.model;

/**
 *
 * @author Lucas Souza [sorackb@gmail.com]
 */
public class Bet {
  private Integer initialBet;
  private Integer nextBet;

  public Integer getInitialBet() {
    return initialBet;
  }

  public void setInitialBet(Integer initialBet) {
    this.initialBet = initialBet;
  }

  public Integer getNextBet() {
    return nextBet;
  }

  public void setNextBet(Integer nextBet) {
    this.nextBet = nextBet;
  }
}
