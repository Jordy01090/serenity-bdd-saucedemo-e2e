Proyecto: Saucedemo E2E con Serenity BDD (Screenplay) + Cucumber

Requisitos:
- Java JDK 11 (o 17)
- Maven 3.8+
- Chrome 126+ (o versión estable)
- SO probado: Windows 10/11

Dependencias clave:
- serenity-core 3.6.12
- serenity-junit 3.6.12
- serenity-cucumber6 3.6.12
- serenity-screenplay / serenity-screenplay-webdriver 3.6.12
- selenium-java 4.22.0
- webdrivermanager 5.8.0

Cómo ejecutar:
1) Clonar el repo
2) En la raíz, ejecutar:
   mvn clean verify
3) Abrir el reporte de Serenity:
   target/site/serenity/index.html

Credenciales usadas:
- Usuario: standard_user
- Password: secret_sauce

Notas:
- El driver de Chrome se gestiona automáticamente con WebDriverManager.
- Screenshots: activados (AFTER_EACH_STEP).
- Los steps están en: starter.stepdefinitions
- Runner de Cucumber: starter.runners.PurchaseCucumberRunner
