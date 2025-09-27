Selenium Certification 101 (Java + Selenium + TestNG)

Automatización de escenarios web con Selenium 4, Java 17 y TestNG, ejecutando cross-browser en paralelo sobre Selenium Grid (LambdaTest) con artefactos habilitados (network logs, video, screenshots y console logs).

Este repo está preparado para la Certificación 101 y sirve de base para la 102.

🧰 Requisitos

Java 17 (JDK 17)

Maven 3.9+

Cuenta en LambdaTest (usuario y access key)

TestNG (se usa vía Maven Surefire)

(Opcional) Eclipse o IntelliJ IDEA

(Opcional) Gitpod (entrega recomendada por el examen)

🔐 Variables de entorno (LambdaTest)

Configura estas variables antes de ejecutar en Grid:

LT_USERNAME — tu usuario de LambdaTest

LT_ACCESS_KEY — tu access key de LambdaTest

Windows (PowerShell):

setx LT_USERNAME "tu_usuario"
setx LT_ACCESS_KEY "tu_access_key"


macOS / Linux (bash/zsh):

export LT_USERNAME="tu_usuario"
export LT_ACCESS_KEY="tu_access_key"


Eclipse (Run Configurations → Environment):

Add… → Name=LT_USERNAME, Value=tu_usuario

Add… → Name=LT_ACCESS_KEY, Value=tu_access_key

🏗️ Estructura del proyecto
.
├─ pom.xml
├─ testng.xml
├─ .gitpod.yml
└─ src
   └─ test
      └─ java
         └─ com
            └─ cert
               └─ tests
                  ├─ grid
                  │  ├─ BaseGridTest.java
                  │  └─ day1
                  │     ├─ WikipediaSearchRemote.java
                  │     └─ EcommerceSearchRemote.java
                  └─ local
                     └─ (opcional) ...

⚙️ Configuración clave (Grid + artefactos)

En BaseGridTest.java se definen capacidades W3C y LT:Options:

network=true (Network logs)

video=true (Video recording)

visual=true (Screenshots)

console=true (Console logs)

build, project, name (metadatos)

Marcado de estado del job: lambda-status=passed/failed en @AfterMethod

Nota: Para evitar errores de sintaxis de JS, no enviamos lambda-notes (el guion puede romper el identificador si no se intercepta). El status es suficiente para la revisión.

▶️ Cómo ejecutar
Opción A — Eclipse (recomendado para desarrollo)

Importa como Existing Maven Project (si no lo has hecho).

Abre testng.xml.

Right Click → Run As → TestNG Suite
(o Run As → Maven test).

Esto lanzará la suite en paralelo (Chrome, Firefox, Edge) sobre Windows 11 en LambdaTest.

Opción B — Maven CLI
# En la raíz del proyecto:
mvn -q test


Reportes: target/surefire-reports/

🧪 Suite TestNG (paralelo 3 navegadores)

testng.xml ya viene configurado con:

<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
<suite name="Cert Suite" parallel="tests" thread-count="3">
  <test name="Day1-Grid-Chrome">
    <parameter name="browserName" value="Chrome"/>
    <parameter name="browserVersion" value="latest"/>
    <parameter name="platformName" value="Windows 11"/>
    <groups><run><include name="day1"/></run></groups>
    <classes>
      <class name="com.cert.tests.grid.day1.WikipediaSearchRemote"/>
      <class name="com.cert.tests.grid.day1.EcommerceSearchRemote"/>
    </classes>
  </test>

  <test name="Day1-Grid-Firefox">
    <parameter name="browserName" value="Firefox"/>
    <parameter name="browserVersion" value="latest"/>
    <parameter name="platformName" value="Windows 11"/>
    <groups><run><include name="day1"/></run></groups>
    <classes>
      <class name="com.cert.tests.grid.day1.WikipediaSearchRemote"/>
      <class name="com.cert.tests.grid.day1.EcommerceSearchRemote"/>
    </classes>
  </test>

  <test name="Day1-Grid-Edge">
    <parameter name="browserName" value="MicrosoftEdge"/>
    <parameter name="browserVersion" value="latest"/>
    <parameter name="platformName" value="Windows 11"/>
    <groups><run><include name="day1"/></run></groups>
    <classes>
      <class name="com.cert.tests.grid.day1.WikipediaSearchRemote"/>
      <class name="com.cert.tests.grid.day1.EcommerceSearchRemote"/>
    </classes>
  </test>
</suite>

🌐 ¿Qué validan los tests?

WikipediaSearchRemote: búsqueda en Wikipedia y verificación de resultados.

EcommerceSearchRemote: búsqueda de “iPhone” en Ecommerce Playground, apertura de detalle y verificación de H1.

Paralelo en 3 navegadores y artefactos habilitados en cada sesión.

🧪 Ver evidencias en LambdaTest

Ve a Automation en el panel de LambdaTest.

Abre cada Session del build Cert-101-Java.

Verás:

Video recording

Screenshots

Network logs

Console logs

Status (Passed/Failed)

🖥️ Gitpod (requisito de la certificación)

Este repo incluye .gitpod.yml. Para ejecutar en Gitpod:

Abre el repo en Gitpod.

En Variables de entorno de Gitpod, añade:

LT_USERNAME

LT_ACCESS_KEY

En la terminal de Gitpod:

mvn -q test


Verifica en LambdaTest que los artefactos se generaron.

🚑 Troubleshooting

Warnings de JDK/Jansi/Unsafe/SLF4J al iniciar Maven:
Son benignos para la certificación. No afectan la ejecución de las pruebas.
Si quieres silenciarlos:

Jansi: añade --enable-native-access=ALL-UNNAMED al arranque del JVM (opcional).

SLF4J: puedes añadir un binder (no necesario para el examen).

IllegalStateException: Define LT_USERNAME/LT_ACCESS_KEY
Asegúrate de tener las variables de entorno en el sistema o configuradas en el entorno de ejecución (Eclipse/Gitpod/CLI).

El job no marca Passed/Failed
Verifica que @AfterMethod ejecute ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
y que no haya excepciones en teardown.

lambda-notes da SyntaxError: invalid assignment left-hand side
No lo usamos para evitar ese problema. El status es suficiente para la evaluación.

📦 Entrega (lo que pide el examen)

✅ Repo en GitHub privado con .gitpod.yml.

✅ Compartido con admin@lambdatestcertifications.com
.

✅ Ejecución Grid en paralelo (Chrome/Firefox/Edge).

✅ Network logs, video, screenshots y console logs habilitados.

✅ Status de cada job (Passed/Failed).

✅ README (este archivo) explicando cómo ejecutar.

⏱️ Entrega del assignment dentro del plazo (recomiendan 36 h antes del deadline).

🎯 ≥ 70% para certificar.

📜 Licencia

Uso educativo para la certificación. Ajusta según tus necesidades.

🙋‍♂️ Contacto

Cualquier duda, revisa Issues del repo