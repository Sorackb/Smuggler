package org.lucassouza.smuggler.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lucassouza.smuggler.model.CSGODouble;
import org.lucassouza.smuggler.model.History;

/**
 *
 * @author Lucas Souza [sorackb@gmail.com]
 */
public class Main {

  public static void main(String[] args) {
    //CSGODouble csGoDouble = new CSGODouble();
    History history = new History();

    /*try {
      csGoDouble.startBetting();
    } catch (Exception ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }*/
    
    try {
      history.run();
    } catch (Exception ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
