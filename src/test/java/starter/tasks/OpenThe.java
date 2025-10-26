package starter.tasks;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class OpenThe {
  public static Task application() {
    return Task.where("{0} opens the application", Open.url("https://www.saucedemo.com/"));
  }
}
