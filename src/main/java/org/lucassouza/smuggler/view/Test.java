package org.lucassouza.smuggler.view;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.lucassouza.smuggler.model.Bet;
import org.lucassouza.smuggler.model.Roulette;
import org.lucassouza.smuggler.type.Color;
import static org.lucassouza.smuggler.type.Color.BLACK;
import static org.lucassouza.smuggler.type.Color.GREEN;
import static org.lucassouza.smuggler.type.Color.RED;

/**
 *
 * @author lucas.souza
 */
public class Test {

  private static Roulette roulette;

  public static void main(String[] args) {
    //CSGODouble csGoDouble = new CSGODouble();
    roulette = new Roulette(10000, true, BLACK, RED);
    HashMap<Color, Bet> bet = roulette.getBet();

    System.out.println("BLACK: " + bet.get(BLACK).getBind() + " / RED: " + bet.get(RED).getBind());
    System.out.println("Balance: " + roulette.getProvisionalBalance());
    System.out.println("=======  0  =======");

    for (int i = 0; i < 960; i++) {
      execute();
    }
  }

  private static void execute() {
    Boolean accurate = true;

    if (accurate) {
      runAccurate();
    } else {
      runNotAccurate();
    }
  }

  private static void runAccurate() {
    Random random = new Random();
    float chance = random.nextFloat();

    if (chance <= 0.06f) {
      roulette.roll(GREEN);
    } else if (chance <= 0.53f) {
      roulette.roll(RED);
    } else {
      roulette.roll(BLACK);
    }
  }

  private static void runNotAccurate() {
    Integer chance2 = ThreadLocalRandom.current().nextInt(1, 2 + 1);

    if (chance2 == 1) {
      roulette.roll(RED);
    } else {
      roulette.roll(BLACK);
    }
  }
}
