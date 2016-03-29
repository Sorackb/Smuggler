package org.lucassouza.smuggler.type;

import org.openqa.selenium.By;

/**
 *
 * @author Lucas Souza [lucas.souza@virtual.inf.br]
 */
public enum Selector {
  CSS_SELECTOR, ID;

  public static By getLocator(Selector selector, String identifier) {
    By locator;

    switch (selector) {
      case CSS_SELECTOR:
        locator = By.cssSelector(identifier);
        break;
      default:
        locator = By.id(identifier);
    }

    return locator;
  }
}
