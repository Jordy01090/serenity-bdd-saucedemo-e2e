package starter.stepdefinitions;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
  @Before(order = 0)
  public void setUpDriverBinary() {
    WebDriverManager.chromedriver().setup();
  }
}
