package org.lucassouza.smuggler.view;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.lucassouza.smuggler.model.CSGODouble;
import org.lucassouza.smuggler.model.Roulette;
import static org.lucassouza.smuggler.type.Color.BLACK;
import static org.lucassouza.smuggler.type.Color.RED;

/**
 *
 * @author lucas.souza
 */
public class Test {
  private static Roulette roulette;

  public static void main(String[] args) {
    CSGODouble csGoDouble = new CSGODouble();

    csGoDouble.startBetting();
    /*roulette = new Roulette(500, BLACK, RED);
    HashMap<Color, Bet> bet = roulette.getBet();

    System.out.println("BLACK: " + bet.get(BLACK).getNextBet() + " / RED: " + bet.get(RED).getNextBet());
    System.out.println("Balanço: " + roulette.getBalance());
    System.out.println("======================");

    for (int i = 0; i < 2880; i++) {
      execute();
    }*/
  }
  
  private static void execute() {
    Random random = new Random();
    //float chance = random.nextFloat();
    Integer chance2 = ThreadLocalRandom.current().nextInt(1, 2 + 1);
    
    if (chance2 == 1) {
      roulette.roll(RED);
    } else {
      roulette.roll(BLACK);
    }

    /*if (chance <= 0.06f) {
      roulette.roll(GREEN);
    } else if (chance <= 0.53f) {
      roulette.roll(RED);
    } else {
      roulette.roll(BLACK);
    }*/
  }
}
