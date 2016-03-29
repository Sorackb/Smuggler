package org.lucassouza.smuggler.model;

import java.util.HashMap;
import java.util.Set;
import org.lucassouza.smuggler.type.Color;
import org.lucassouza.smuggler.type.Selector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Lucas Souza [lucas.souza@virtual.inf.br]
 */
public class CSGODouble {

  private final WebDriver driver;
  private Roulette roulette;

  public CSGODouble() {
    //System.setProperty("webdriver.chrome.driver", "C:/lucassouza.org/Smuggler/chromedriver.exe");
    //this.driver = new ChromeDriver();
    this.driver = new FirefoxDriver();
  }

  public void startBetting() {
    Integer balance;

    this.driver.get("http://www.csgodouble.com/index.php");
    System.out.println("Autenticar manualmente");
    this.waitBetPhase();
    // TODO Colocar a leitura real de valor
    balance = 500;
    //balance = Integer.parseInt(this.getText(Selector.ID, "balance"));
    this.roulette = new Roulette(balance, Color.RED, Color.BLACK);
    this.betNext();
  }

  private void betNext() {
    HashMap<Color, Bet> bet;
    Set<Color> colors;

    bet = this.roulette.getBet();
    colors = bet.keySet();
    this.waitBetPhase();

    for (Color color : colors) {
      this.betColor(color, bet.get(color).getNextBet());
    }

    this.checkResult();
  }

  private void betColor(Color color, Integer amount) {
    WebElement betAmountInput;
    String panelId;

    panelId = color.getPanelId();
    betAmountInput = this.findElement(Selector.ID, "betAmount");
    betAmountInput.click();
    betAmountInput.clear();
    betAmountInput.sendKeys(amount.toString());
    this.waitClickable(Selector.CSS_SELECTOR, "div#" + panelId + " > div > button", 5);
    this.findElement(Selector.CSS_SELECTOR, "div#" + panelId + " > div > button").click();
    // TODO Habilitar quando houver valor
    //this.waitTextToBePresent(Selector.CSS_SELECTOR, "div#" + panelId + " div.mytotal", amount.toString(), 5);
  }
  
  private void checkResult() {
    Integer result;

    this.waitTextToBePresent(Selector.ID, "banner", "CSGODouble rolled ", 90);
    result = Integer.parseInt(this.getText(Selector.CSS_SELECTOR, "div#past > div:last-child"));
    this.roulette.roll(result);
    this.betNext();
  }

  private WebElement findElement(Selector selector, String identifier) {
    return this.driver.findElement(Selector.getLocator(selector, identifier));
  }

  private String getText(Selector selector, String identifier) {
    return this.findElement(selector, identifier).getText();
  }

  private void waitBetPhase() {
    this.waitTextToBePresent(Selector.ID, "banner", "Rolling in", 90);
  }

  private void waitTextToBePresent(Selector selector, String identifier, String text, Integer timeout) {
    WebDriverWait wait;

    wait = new WebDriverWait(this.driver, timeout);
    wait.until(ExpectedConditions.textToBePresentInElementLocated(Selector.getLocator(selector, identifier), text));
  }

  private void waitClickable(Selector selector, String identifier, Integer timeout) {
    WebDriverWait wait;

    wait = new WebDriverWait(this.driver, timeout);
    wait.until(ExpectedConditions.elementToBeClickable(Selector.getLocator(selector, identifier)));
  }
}
