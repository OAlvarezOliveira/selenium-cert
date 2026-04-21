# 🧪 Selenium Java 101 Certification – LambdaTest

![Certification Status](https://img.shields.io/badge/Selenium%20Java%20101-Passed-brightgreen?style=for-the-badge&logo=checkmarx)

Autor: Óscar Álvarez Oliveira

Repositorio: https://github.com/OAlvarezOliveira/selenium-cert 

Certificación: Selenium Java 101 – LambdaTest

Fecha de entrega: 26 October 2025

---

## 🏆 Achievements

- Successfully completed the **Selenium Java 101 Certification** from LambdaTest.  
- Achieved a **76% score**, above the required 70% passing threshold.  
- Built a complete Selenium automation suite with:
  - **Java 17**, **Selenium 4**, **TestNG**, **Maven**
  - Parallel execution: **Chrome (Win10)**, **Edge (macOS)**, **Firefox (Win11)**
  - Remote execution on **LambdaTest Selenium Grid**
  - Full test artifacts enabled (video, screenshots, network logs, console logs)
  - Advanced Cookiebot removal (DOM + shadowRoot + retry loop)
  - Slider precision using JavaScript events (`input` + `change`)
- Repository validated and accepted by the **LambdaTest Certifications Team**.

---

## ✅ Resumen general

| Métrica | Resultado |
|--------|-----------|
| Total de tests | **9** |
| Tests aprobados | **9 / 9 (100%)** |
| Fallos | **0** |
| Skipped | **0** |
| Paralelismo | 3 navegadores × 3 escenarios |
| Grid | LambdaTest Selenium Cloud |
| Artefactos | Video 🎥 · Screenshots 📸 · Console 🧾 · Network 🌐 |

---

## 🧠 Escenarios automatizados

### 1️⃣ Simple Form Demo
- Navega a *Selenium Playground* → **Simple Form Demo**  
- Valida que el mensaje introducido aparece bajo **Your Message**.

### 2️⃣ Drag & Drop Sliders
- Abre **Drag & Drop Sliders**  
- Ajusta el *Default value 15* hasta **95** mediante JavaScript  
- Valida que el valor mostrado sea **95**

### 3️⃣ Input Form Submit
- Abre **Input Form Submit**  
- Verifica la validación HTML5 al enviar vacío  
- Rellena todos los campos (país: *United States*)  
- Envía el formulario y valida:  
  **“Thanks for contacting us, we will get back to you shortly.”**  
- Implementación anti-Cookiebot: DOM + shadow DOM + reintentos

---

## ⚙️ Configuración del entorno

```bash
# Variables de entorno
export LT_USERNAME="oalvarezoliveira"
export LT_ACCESS_KEY="YOUR_ACCESS_KEY"

# Ejecución limpia
mvn clean
mvn -q test

Ejecutado en ONA (Gitpod) con Java 17 + Maven 3.8 + TestNG.

🧩 Capacidades técnicas

Paralelismo: parallel="tests"

Timeouts: 20–60 s

Selectores: id, name, linkText, cssSelector, xpath

Actualización precisa de slider: JavaScript value + change event

Limpieza de cookies avanzada (Cookiebot DOM + shadowRoot)

Artefactos habilitados: video, screenshots, console logs, network logs

Marcado de estado: lambda-status=passed/failed

🧾 LambdaTest Session IDs (oficiales)
Navegador / SO	Session ID
Chrome (Windows 10)	DA-WIN-2801943-1761480744430829019WVU
Edge (macOS Ventura)	DA-WIN-2801943-1761480713410716439TES
Firefox (Windows 11)	DA-WIN-2801943-1761480712138821776VYE


🔍 Detalle de ejecución (9 tests)
#	Test	Navegador / SO	Session ID
1	SimpleFormTest	Chrome (Win10)	DA-WIN-2801943-1761480744430829019WVU
2	SimpleFormTest	Edge (macOS)	DA-WIN-2801943-1761480713410716439TES
3	SimpleFormTest	Firefox (Win11)	DA-WIN-2801943-1761480712138821776VYE
4	DragDropSliderTest	Chrome (Win10)	DA-MAC-2801943-1761480700705416016MMQ
5	DragDropSliderTest	Edge (macOS)	DA-WIN-2801943-1761480686493595660YNX
6	DragDropSliderTest	Firefox (Win11)	DA-MAC-2801943-1761480682142685787XQN
7	InputFormSubmitTest	Chrome (Win10)	DA-WIN-2801943-1761480670193493606KFF
8	InputFormSubmitTest	Edge (macOS)	DA-WIN-2801943-1761480670026143265UAU
9	InputFormSubmitTest	Firefox (Win11)	DA-MAC-2801943-1761480669970444976UBX

🧠 Notas de compatibilidad

Internet Explorer 11 excluido por obsolescencia en LambdaTest

Cookiebot presenta reinyección tardía en navegadores Chromium → solucionado con reintentos

Slider no actualiza el label en Grid → ajustado con JavaScript para estabilidad

🏅 Certification Awarded

Certification: Selenium Java 101 – LambdaTest
Candidate: Óscar Álvarez Oliveira
Score: 76% (Minimum required: 70%)
Status: ✔ Passed
Issued on: 26 October 2025

This repository contains the complete automation assignment officially reviewed and accepted by the LambdaTest Certifications Team.


Estado final
BUILD SUCCESS
Tests run: 9
Failures: 0
Errors: 0
Skipped: 0
All tests passed successfully on LambdaTest Grid.

Repository shared with:

LambdaTest-Certifications
admin@lambdatestcertifications.com
