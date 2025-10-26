package starter.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CheckoutPage {
  public static final Target FIRST_NAME = Target.the("First Name").locatedBy("#first-name");
  public static final Target LAST_NAME  = Target.the("Last Name").locatedBy("#last-name");
  public static final Target ZIP        = Target.the("Zip").locatedBy("#postal-code");
  public static final Target CONTINUE   = Target.the("Continue").locatedBy("#continue");
  public static final Target FINISH     = Target.the("Finish").locatedBy("#finish");
  public static final Target COMPLETE_HEADER = Target.the("complete header").locatedBy(".complete-header");
}
