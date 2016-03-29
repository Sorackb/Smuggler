package org.lucassouza.smuggler.model;

import java.util.HashMap;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.lucassouza.smuggler.type.Color;

/**
 *
 * @author Lucas Souza [sorackb@gmail.com]
 */
public class Roulette {

  private static final Double MAX_TRY = 11.0; // Número máximo de tentativas
  private final HashMap<Color, Bet> bet;
  private final Boolean applyGrandMartingale;
  private Integer sequence;
  private Integer initialBet;
  private Integer provisionalBalance;

  public Roulette(Integer initialBalance, Color... colors) {
    this(initialBalance, false, colors);
  }

  public Roulette(Integer initialBalance, Boolean applyGrandMartingale, Color... colors) {
    this.bet = new HashMap<>();
    this.sequence = 0;
    this.provisionalBalance = initialBalance;
    this.applyGrandMartingale = applyGrandMartingale;
    this.recalculateInitialBet();

    for (Color color : colors) {
      Bet newBet = new Bet();

      newBet.setBind(this.initialBet);
      this.bet.put(color, newBet);
    }
  }

  public void roll(Integer number) {
    HashMap<Color, Bet> bet;

    for (Color color : Color.values()) {
      if (number >= color.getStart() && number <= color.getEnd()) {
        this.roll(color);
      }
    }
  }

  public void roll(Color color) {
    Integer difference = 0;
    Boolean dangerous = false;
    Boolean stopped = false;
    String summary = "";
    Set<Color> keys;

    System.out.println(color.name() + " rolled");
    keys = this.bet.keySet();

    for (Color key : keys) {
      Bet actualBet;
      Integer nextBind;

      actualBet = this.bet.get(key);
      nextBind = actualBet.getBind() * 2;

      // Aplica a estratégia Grand Martingale que adiciona o valor da aposta a cada nova tentativa
      if (this.applyGrandMartingale) {
        nextBind = nextBind + this.initialBet;
      }

      if (key.equals(color)) {
        this.setProvisionalBalance(this.provisionalBalance + actualBet.getTotal());
        actualBet.setBind(this.initialBet);
      } else if (this.provisionalBalance - nextBind < 0) { // Evita valores negativos parando a aposta
        //} else if (this.provisionalBalance - nextBind < nextBind) { // Evita valores negativos parando a aposta
        stopped = true;
        actualBet.setBind(this.initialBet);
      } else {
        this.setProvisionalBalance(this.provisionalBalance - nextBind);
        actualBet.setBind(nextBind);
      }

      difference = difference + actualBet.getBind();

      if (!summary.isEmpty()) {
        summary = summary + " / ";
      }

      summary = summary + key.name() + ": " + actualBet.getBind();

      if (actualBet.getBind() > this.provisionalBalance) {
        dangerous = true;
      }
    }

    if (stopped) {
      summary = summary + " <= STOPPED";
    } else if (dangerous) {
      summary = summary + " <= DANGER";
    }

    System.out.println(summary);
    System.out.println("Balance: " + this.provisionalBalance + " / " + (this.provisionalBalance + difference));
    this.sequence = this.sequence + 1;
    System.out.println("=======" + StringUtils.center(this.sequence.toString(), 5) + "=======");
  }

  public HashMap<Color, Bet> getBet() {
    return this.bet;
  }

  public void setProvisionalBalance(Integer provisionalBalance) {
    this.provisionalBalance = provisionalBalance;
    this.recalculateInitialBet();
  }

  public Integer getProvisionalBalance() {
    return this.provisionalBalance;
  }

  private void recalculateInitialBet() {
    Integer newInitialBet;
    Double root;

    root = this.provisionalBalance / Math.pow(2, MAX_TRY);
    newInitialBet = root.intValue();

    if (newInitialBet == 0) {
      newInitialBet = 1;
    }

    this.initialBet = newInitialBet;
  }
}
