package starter.tasks;

import static starter.ui.LoginPage.*;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Click;

public class Login {
  public static Task with(String user, String pass) {
    return Task.where("{0} logs in",
        Enter.theValue(user).into(USERNAME),
        Enter.theValue(pass).into(PASSWORD),
        Click.on(LOGIN_BUTTON)
    );
  }
}
