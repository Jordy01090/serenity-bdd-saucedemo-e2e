package starter.stepdefinitions;

import io.cucumber.java.es.*;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

import starter.tasks.*;
import starter.questions.OrderMessage;

public class PurchaseSteps {

  @Managed(driver = "chrome")
  WebDriver hisBrowser;

  private Actor jordy;

  @Before
  public void setUp() {
    jordy = Actor.named("Jordy");
    jordy.can(BrowseTheWeb.with(hisBrowser));
  }

  @Dado("que Jordy abre la aplicación")
  public void queJordyAbreLaAplicacion() {
    jordy.attemptsTo(OpenThe.application());
  }

  @Cuando("inicia sesión con credenciales válidas")
  public void iniciaSesionConCredencialesValidas() {
    jordy.attemptsTo(Login.with("standard_user", "secret_sauce"));
  }

  @Y("agrega dos productos al carrito")
  public void agregaDosProductosAlCarrito() {
    jordy.attemptsTo(AddTwoProducts.toCart());
  }

  @Y("completa el checkout con sus datos")
  public void completaElCheckoutConSusDatos() {
    jordy.attemptsTo(Checkout.complete("Jordy", "Avila", "170201"));
  }

  @Entonces("ve el mensaje de confirmación de la orden")
  public void veElMensajeDeConfirmacionDeLaOrden() {
    jordy.should(seeThat("Mensaje final",
        OrderMessage.text(), equalTo("THANK YOU FOR YOUR ORDER")));
  }
}
