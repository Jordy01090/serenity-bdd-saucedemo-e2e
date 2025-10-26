Feature: E2E de compra en Saucedemo

  Scenario: Usuario completa una compra exitosa
    Given que Jordy abre la aplicación
    When inicia sesión con credenciales válidas
    And agrega dos productos al carrito
    And completa el checkout con sus datos
    Then ve el mensaje de confirmación de la orden
