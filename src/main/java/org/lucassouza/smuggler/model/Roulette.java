package org.lucassouza.smuggler.model;

import java.util.HashMap;
import java.util.Set;
import org.lucassouza.smuggler.type.Color;

/**
 *
 * @author Lucas Souza [sorackb@gmail.com]
 */
public class Roulette {

  private static final Double MAX_TRY = 8.0; // Número máximo de tentativas
  private final HashMap<Color, Bet> bet;
  private Integer initialBet;
  private Integer balance;

  public Roulette(Integer balance, Color... colors) {
    this.bet = new HashMap<>();
    this.balance = balance;
    this.recalculateInitialBet();

    for (Color color : colors) {
      Bet newBet = new Bet();

      newBet.setInitialBet(this.initialBet);
      newBet.setNextBet(this.initialBet);
      this.bet.put(color, newBet);
    }
  }

  public void roll(Color color) {
    String summary = "";
    Set<Color> keys;

    System.out.println(color.name() + " rolled");
    keys = this.bet.keySet();

    for (Color key : keys) {
      Bet actualBet = this.bet.get(key);

      if (key.equals(color)) {
        this.setBalance(this.balance + actualBet.getInitialBet());
        actualBet.setInitialBet(this.initialBet);
        actualBet.setNextBet(this.initialBet);
      } else {
        actualBet.setNextBet(actualBet.getNextBet() * 2);
      }

      if (!summary.isEmpty()) {
        summary = summary + " / ";
      }

      summary = summary + key.name() + ": " + actualBet.getNextBet();
      
      if (actualBet.getNextBet() > this.balance) {
        summary = summary + " <= LEAK";
      }
    }
    
    System.out.println(summary);
    System.out.println("Balance: " + this.balance);
    System.out.println("======================");
  }

  public HashMap<Color, Bet> getBet() {
    return this.bet;
  }

  public void setBalance(Integer balance) {
    this.balance = balance;
    this.recalculateInitialBet();
  }

  public Integer getBalance() {
    return this.balance;
  }
  
  private void recalculateInitialBet() {
    Integer newInitialBet;
    Double root;

    root = this.balance / Math.pow(2, MAX_TRY);
    newInitialBet = root.intValue();
    
    if (newInitialBet == 0) {
      newInitialBet = 1;
    }

    this.initialBet = newInitialBet;
  }
}
