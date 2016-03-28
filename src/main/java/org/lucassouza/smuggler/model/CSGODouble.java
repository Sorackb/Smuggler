package org.lucassouza.smuggler.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Lucas Souza [lucas.souza@virtual.inf.br]
 */
public class CSGODouble {

  private final WebDriver driver;

  public CSGODouble() {
    System.setProperty("webdriver.chrome.driver", "C:/Smuggler/chromedriver.exe");

    this.driver = new ChromeDriver();
  }
  
  public void startSmuggling() {
    this.driver.get("http://www.csgodouble.com/index.php");
    System.out.println("Autenticar manualmente");
  }
}
