package starter.questions;

import static starter.ui.CheckoutPage.COMPLETE_HEADER;

import net.serenitybdd.screenplay.Question;

public class OrderMessage {
  public static Question<String> text() {
    return actor -> COMPLETE_HEADER.resolveFor(actor).getText();
  }
}
