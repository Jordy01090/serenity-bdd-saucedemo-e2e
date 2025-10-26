package starter.ui;

import net.serenitybdd.screenplay.targets.Target;

public class LoginPage {
  public static final Target USERNAME = Target.the("username").locatedBy("#user-name");
  public static final Target PASSWORD = Target.the("password").locatedBy("#password");
  public static final Target LOGIN_BUTTON = Target.the("login button").locatedBy("#login-button");
}
