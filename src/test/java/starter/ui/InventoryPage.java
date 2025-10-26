package starter.ui;

import net.serenitybdd.screenplay.targets.Target;

public class InventoryPage {
  public static final Target ADD_BACKPACK = Target.the("add backpack").locatedBy("#add-to-cart-sauce-labs-backpack");
  public static final Target ADD_BIKE_LIGHT = Target.the("add bike light").locatedBy("#add-to-cart-sauce-labs-bike-light");
  public static final Target CART_ICON = Target.the("cart icon").locatedBy(".shopping_cart_link");
}
