# Saucedemo E2E — Serenity BDD (Screenplay) + Cucumber

**Autor:** Jordy Ávila  
**Fecha:** Octubre 2025  

Automatización E2E del flujo de compra en [SauceDemo](https://www.saucedemo.com) usando **Serenity BDD** con el patrón **Screenplay** y **Cucumber** (Java). El objetivo es validar el recorrido completo desde el _login_ hasta la confirmación de la orden.

---

## 🧭 Tabla de contenidos
- [Arquitectura y Stack](#arquitectura-y-stack)
- [Estructura del repositorio](#estructura-del-repositorio)
- [Requisitos previos](#requisitos-previos)
- [Instalación y ejecución (paso a paso)](#instalación-y-ejecución-paso-a-paso)
- [Configuración (serenity.conf)](#configuración-serenityconf)
- [Cómo correr escenarios y filtrar por tags](#cómo-correr-escenarios-y-filtrar-por-tags)
- [Reportes](#reportes)
- [Buenas prácticas aplicadas (Screenplay)](#buenas-prácticas-aplicadas-screenplay)
- [Solución de problemas (FAQ)](#solución-de-problemas-faq)
- [CI/CD (GitHub Actions ejemplo)](#cicd-github-actions-ejemplo)
- [Licencia](#licencia)

---

## Arquitectura y Stack

- **Lenguaje:** Java 17.0.12  
- **Build:** Maven 3.9.11  
- **Framework BDD:** Serenity BDD (Screenplay) + Cucumber  
- **Driver Web:** Selenium 4 con autodetección y descarga automática de ChromeDriver  
- **Navegador por defecto:** Google Chrome (estable)  
- **Asserts:** AssertJ / Hamcrest (vía Serenity)  

Versiones de referencia probadas y alineadas:
- `serenity-core` / `serenity-screenplay` / `serenity-cucumber`: **3.6.23**
- `selenium-java`: **4.19.1**

> El proyecto está configurado para descargar automáticamente el **ChromeDriver** compatible con la versión de Chrome instalada en el equipo del usuario (no se requiere path manual).

---

## Estructura del repositorio

```
.
├─ pom.xml
├─ serenity.conf
├─ src
│  └─ test
│     ├─ java
│     │  └─ starter
│     │     ├─ runners/                # Runner de Cucumber + Serenity
│     │     ├─ stepdefinitions/        # Step Definitions en español
│     │     ├─ tasks/                  # Tareas Screenplay (acciones)
│     │     ├─ questions/              # Preguntas/validaciones
│     │     └─ ui/                     # Mapeo de elementos de UI
│     └─ resources
│        └─ features
│           └─ purchase/purchase_flow.feature
└─ target/site/serenity/index.html     # Reporte HTML (se genera al ejecutar)
```

---

## Requisitos previos

1. **Java JDK 17+**  
   Verifica con:
   ```bash
   java -version
   ```

2. **Maven 3.9.11**  
   Verifica con:
   ```bash
   mvn -version
   ```

3. **Google Chrome** instalado (versión estable).  
   - En caso de entornos corporativos con políticas restrictivas, asegúrate de que Chrome pueda ejecutarse.

4. **Permisos** para que el sistema descargue y ejecute binarios (ChromeDriver) en el perfil del usuario.

> _No necesitas descargar ChromeDriver manualmente_. El proyecto está configurado con `autodownload = true` (Serenity lo gestiona).

---

## Instalación y ejecución (paso a paso)

### 1) Clonar el repositorio
```bash
git clone https://github.com/<tu-usuario>/<tu-repo>.git
cd <tu-repo>
```

### 2) Verificar herramientas
```bash
java -version
mvn -version
```

### 3) Ejecutar las pruebas
```bash
mvn clean verify -U
```
- `-U` fuerza actualización de dependencias si es necesario.
- La primera ejecución descargará dependencias y el driver de navegador (puede tardar más).

### 4) Ver el reporte
Abre el reporte HTML en tu navegador:
```
target/site/serenity/index.html
```

---

## Configuración (`serenity.conf`)

El proyecto usa **Chrome** por defecto y descarga el driver automáticamente:

```hocon
webdriver {
  driver = chrome
  autodownload = true
  capabilities {
    "goog:chromeOptions" {
      args = [
        "--start-maximized",
        "--disable-extensions",
        "--remote-allow-origins=*"
      ]
    }
  }
}
```

> Si prefieres configurar un ChromeDriver **manual** (no recomendado salvo entornos aislados), cambia `autodownload = false` y agrega `chrome.driver = "C:/ruta/al/chromedriver.exe"`.

---

## Cómo correr escenarios y filtrar por tags

El **Runner** principal es `starter.runners.PurchaseCucumberRunner`.  
Puedes añadir tags en el `.feature` y filtrarlos con `-Dcucumber.filter.tags`:

```bash
mvn clean verify -Dcucumber.filter.tags="@smoke"
```

Ejemplos de tags útiles:
- `@e2e` para escenarios end-to-end
- `@smoke` para fumadores rápidos
- `@regression` para regresión completa

> También puedes ajustar `@CucumberOptions` en el Runner para incluir/excluir rutas específicas.

---

## Reportes

Serenity genera un reporte HTML navegable con:
- Resumen de ejecuciones, resultados y tiempos.
- Evidencia paso a paso (capturas si está habilitado).
- Métricas por feature/escenario.

Ruta del reporte:  
```
target/site/serenity/index.html
```

---

## Buenas prácticas aplicadas (Screenplay)

- **Separación de responsabilidades:**  
  `ui/` (selectores) | `tasks/` (acciones) | `questions/` (validaciones).

- **Reutilización y legibilidad:**  
  Tareas y preguntas atómicas, fáciles de recombinar según sea necesario.

- **Independencia del driver:**  
  `autodownload = true` evita acoplarse a rutas locales.

- **Asserts robustos:**  
  Se recomienda usar comparaciones tolerantes a cambios menores de copy (p. ej., igualdad ignorando mayúsculas).

---

## Solución de problemas (FAQ)

### 1) `Could not instantiate class org.openqa.selenium.chrome.ChromeDriver`
- Asegúrate de tener **Chrome** instalado y actualizado.
- Limpia cualquier driver cacheado y vuelve a ejecutar:
  ```powershell
  if (Test-Path "$env:USERPROFILE\.cache\selenium") { Remove-Item -Recurse -Force "$env:USERPROFILE\.cache\selenium" }
  if (Test-Path "$env:USERPROFILE\.cache\webdriver") { Remove-Item -Recurse -Force "$env:USERPROFILE\.cache\webdriver" }
  if (Test-Path "$env:LOCALAPPDATA\WebDriver") { Remove-Item -Recurse -Force "$env:LOCALAPPDATA\WebDriver" }
  ```
- Verifica que **no** exista un `chromedriver.exe` “colado” en el PATH:
  ```powershell
  where chromedriver
  ```
  (debe no encontrar nada si usas autodownload).

### 2) `session not created: This version of ChromeDriver only supports Chrome X`
- Es una desalineación entre Chrome y ChromeDriver.
- Con `autodownload = true`, vuelve a ejecutar en limpio (`mvn clean verify -U`).

### 3) `NoClassDefFoundError` con clases de Selenium (e.g., `HasFederatedCredentialManagement`)
- Revisa que el `pom.xml` usa versiones alineadas (Serenity 3.7.1 + Selenium 4.19.1).
- Si mezclaste versiones, limpia `.m2` de Selenium/Serenity y recompila.

### 4) El feature en español no corre
- Si usas `# language: es`, asegúrate de escribir:
  - `Característica`, `Escenario`, `Dado`, `Cuando`, `Entonces`, `Y`.
- O elimina la línea `# language: es` y usa `Feature/Scenario/Given/When/Then/And`.

---

## CI/CD (GitHub Actions ejemplo)

Crea `.github/workflows/ci.yml` con:

```yaml
name: CI
on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  test:
    runs-on: windows-latest
    steps:
      - name: Checkout
        uses: actions/checkout@v4

      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: "17"

      - name: Cache Maven
        uses: actions/cache@v4
        with:
          path: ~/.m2/repository
          key: ${{ runner.os }}-maven-${{ hashFiles('**/pom.xml') }}
          restore-keys: |
            ${{ runner.os }}-maven-

      - name: Run tests
        run: mvn -B -U clean verify

      - name: Upload Serenity report
        uses: actions/upload-artifact@v4
        with:
          name: serenity-report
          path: target/site/serenity
```

> En runners Windows, Chrome ya viene instalado y Selenium descargará el driver correcto.

---

## Licencia

Este proyecto se distribuye con fines educativos y de demostración de pruebas E2E con Serenity BDD. Ajusta la licencia según las políticas de tu organización (MIT/Apache-2.0 u otra).

---

### Créditos
- Serenity BDD Team
- Cucumber-JVM
- Selenium Project
- SauceDemo (app demo pública)
