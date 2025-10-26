package starter.tasks;

import static starter.ui.CartPage.*;
import static starter.ui.CheckoutPage.*;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class Checkout {
  public static Task complete(String name, String last, String zip) {
    return Task.where("{0} completes checkout",
        Click.on(CHECKOUT),
        Enter.theValue(name).into(FIRST_NAME),
        Enter.theValue(last).into(LAST_NAME),
        Enter.theValue(zip).into(ZIP),
        Click.on(CONTINUE),
        Click.on(FINISH)
    );
  }
}
