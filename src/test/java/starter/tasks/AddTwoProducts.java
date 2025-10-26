package starter.tasks;

import static starter.ui.InventoryPage.*;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class AddTwoProducts {
  public static Task toCart() {
    return Task.where("{0} adds two products to the cart",
        Click.on(ADD_BACKPACK),
        Click.on(ADD_BIKE_LIGHT),
        Click.on(CART_ICON)
    );
  }
}
