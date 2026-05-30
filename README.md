# 🧪 SauceDemo — Selenium Java BDD Framework

![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![Selenium](https://img.shields.io/badge/Selenium-4.43.0-green?logo=selenium)
![Cucumber](https://img.shields.io/badge/Cucumber-7.34.3-brightgreen?logo=cucumber)
![Log4j2](https://img.shields.io/badge/Log4j2-2.25.4-red)
![Maven](https://img.shields.io/badge/Maven-Build-blue?logo=apachemaven)
![License](https://img.shields.io/badge/License-MIT-yellow)

End-to-end UI test automation framework for [SauceDemo](https://www.saucedemo.com),
built with **Selenium WebDriver**, **Cucumber BDD**, and **Java** following the
**Page Object Model (POM)** design pattern.

---

## 📋 Table of Contents

- [About the Project](#-about-the-project)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Getting Started](#-getting-started)
- [How to Run Tests](#-how-to-run-tests)
- [Test Users](#-test-users)
- [Sprint Coverage](#-sprint-coverage)
- [Branching Strategy](#-branching-strategy)
- [Naming Conventions](#-naming-conventions)

---

## 📖 About the Project

This framework automates the end-to-end testing of the SauceDemo e-commerce
web application. It covers all core user flows including authentication,
product browsing, cart management, and the complete checkout process.

The framework is designed using industry best practices:

- **Page Object Model** — separates locators and actions from test logic
- **BDD with Cucumber** — scenarios written in plain English (Gherkin)
- **Explicit Waits** — stable, flake-resistant test execution
- **Centralized Configuration** — all settings managed via `config.properties`
- **Structured Logging** — Log4j2 with class-level traceability
- **Screenshot on Failure** — auto-captured and attached to the Extent report

---

## 🛠️ Tech Stack

| Tool              | Version  | Purpose               |
|-------------------|----------|-----------------------|
| Java              | 17       | Programming language  |
| Selenium WebDriver| 4.43.0   | Browser automation    |
| Cucumber          | 7.34.3   | BDD framework         |
| JUnit             | 4        | Test runner           |
| Log4j2            | 2.25.4   | Logging               |
| ExtentReports     | 5.1.2    | HTML test reports     |
| Jackson Databind  | 3.1.3    | JSON test data reader |
| Maven             | Latest   | Build & dependency management |

---

## 📁 Project Structure

```
saucedemo-selenium-java-bdd/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── config/
│   │   │   │   └── ConfigReader.java         ← loads config.properties
│   │   │   ├── driver/
│   │   │   │   └── DriverFactory.java        ← browser init & teardown
│   │   │   ├── pages/
│   │   │   │   ├── BasePage.java             ← shared Selenium actions
│   │   │   │   ├── PageInteractions.java     ← interface for page objects
│   │   │   │   └── LoginPage.java            ← login page locators & methods
│   │   │   └── utils/
│   │   │       ├── ExtentReportManager.java  ← report lifecycle
│   │   │       ├── LoggerUtil.java           ← Log4j2 helper
│   │   │       ├── ScreenshotUtil.java       ← failure screenshot capture
│   │   │       ├── TestDataReader.java       ← JSON test data loader
│   │   │       ├── TestUtil.java
│   │   │       ├── WaitUtil.java             ← explicit wait helpers
│   │   │       └── WindowManager.java        ← tab/window switching
│   │   └── resources/
│   │       ├── config.properties             ← browser, URLs, timeouts
│   │       └── log4j2.xml                    ← logging configuration
│   └── test/
│       ├── java/
│       │   ├── runner/
│       │   │   └── TestRunner.java           ← Cucumber JUnit runner
│       │   ├── stepDefinitions/
│       │   │   └── web/
│       │   │       ├── CommonSteps.java      ← shared step definitions
│       │   │       └── LoginSteps.java       ← login-specific steps
│       │   └── support/
│       │       └── Hooks.java                ← before/after scenario hooks
│       └── resources/
│           ├── features/
│           │   ├── authentication/
│           │   │   └── login.feature
│           │   ├── checkoutFlow/             ← sprint 4
│           │   ├── navigation/               ← sprint 5
│           │   ├── productCatalog/           ← sprint 2
│           │   ├── shoppingCart/             ← sprint 3
│           │   └── userBehavior/
│           └── testdata/
│               └── loginData.json
├── test-output/
│   ├── extent-reports/
│   │   └── report.html                       ← generated HTML report
│   └── logs/
│       └── test-run.log                      ← generated run log
├── .gitignore
├── pom.xml
└── README.md
```

---

## ✅ Prerequisites

Make sure you have the following installed before running the framework:

- [Java JDK 17+](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3.8+](https://maven.apache.org/download.cgi)
- [Google Chrome](https://www.google.com/chrome/) or Microsoft Edge
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) (recommended IDE)

---

## 🚀 Getting Started

**1. Clone the repository**
```bash
git clone https://github.com/AboSelimzzz/saucedemo-selenium-java-bdd.git
cd saucedemo-selenium-java-bdd
```

**2. Install dependencies**
```bash
mvn clean install -DskipTests
```

**3. Configure your settings**

Open `src/main/resources/config.properties` and verify:
```properties
browser=chrome
baseUrl=https://www.saucedemo.com
implicitWait=10

# Page URLs (relative — combined with baseUrl at runtime)
productsUrl=/inventory.html
productDetailUrl=/inventory-item.html
cartUrl=/cart.html
checkoutStep1Url=/checkout-step-one.html
checkoutStep2Url=/checkout-step-two.html
checkoutCompleteUrl=/checkout-complete.html
```

> Supported browsers: `chrome`, `edge`

---

## ▶️ How to Run Tests

**Run all tests**
```bash
mvn test
```

**Run by tag**
```bash
# Run smoke tests only
mvn test "-Dcucumber.filter.tags=@smoke"

# Run a specific sprint
mvn test "-Dcucumber.filter.tags=@sprint1"

# Run a specific module
mvn test "-Dcucumber.filter.tags=@login"

# Run full regression suite
mvn test "-Dcucumber.filter.tags=@regression"

# Combine tags
mvn test "-Dcucumber.filter.tags=@smoke and @login"
```

**Run with a specific browser**
```bash
mvn test "-Dbrowser=chrome"
mvn test "-Dbrowser=edge"
```

**Run a specific scenario by line number**
```bash
mvn test "-Dcucumber.features=src/test/resources/features/authentication/login.feature:25"
```

**Run a specific scenario by name**
```bash
mvn test "-Dcucumber.filter.name=Verify that an invalid credentials displays an error message"
```

> On Windows, wrap the entire `-D` argument in quotes as shown above.

**Re-run only failed tests**
```bash
mvn test -Dcucumber.features="@target/cucumber-reports/rerun.txt"
```

**Reports** are generated at `test-output/extent-reports/report.html` after each run.

---

## 👤 Test Users

SauceDemo provides built-in test accounts for different test scenarios:

| Username                  | Password      | Behavior                    |
|---------------------------|---------------|-----------------------------|
| `standard_user`           | `secret_sauce`| ✅ Full normal flow          |
| `locked_out_user`         | `secret_sauce`| 🔒 Login blocked             |
| `problem_user`            | `secret_sauce`| 🐛 UI bugs present           |
| `performance_glitch_user` | `secret_sauce`| 🐢 Slow response             |
| `error_user`              | `secret_sauce`| ❌ Some interactions fail    |
| `visual_user`             | `secret_sauce`| 👁️ Visual/CSS bugs           |

---

## 📊 Sprint Coverage

| Sprint   | Feature Area            | Module Tag    | Sprint Tag  | Status         |
|----------|-------------------------|---------------|-------------|----------------|
| Sprint 1 | Authentication & Login  | `@login`      | `@sprint1`  | ✅ Complete     |
| Sprint 2 | Product Catalog         | `@catalog`    | `@sprint2`  | 🚧 In Progress  |
| Sprint 3 | Shopping Cart           | `@cart`       | `@sprint3`  | ⏳ Pending      |
| Sprint 4 | Checkout Flow           | `@checkout`   | `@sprint4`  | ⏳ Pending      |
| Sprint 5 | Navigation & Edge Cases | `@navigation` | `@sprint5`  | ⏳ Pending      |

---

## 🌿 Branching Strategy

```
main          ← stable, production-ready releases only
└── develop   ← collects completed sprint branches
    ├── feature/sprint-1-login
    ├── feature/sprint-2-catalog
    ├── feature/sprint-3-cart
    ├── feature/sprint-4-checkout
    └── feature/sprint-5-navigation
```

Work is done on `feature/` branches only. A sprint branch merges into
`develop` when all its scenarios pass. `develop` merges into `main`
when all sprints are complete.

---

## 📐 Naming Conventions

| Artifact               | Format                        | Example               |
|------------------------|-------------------------------|-----------------------|
| User Story             | `US-[Sprint#]-[Story#]`       | `US-01-03`            |
| Functional Requirement | `FR-[Module#]-[Req#]`         | `FR-01-04`            |
| Test Case              | `TC-[ModuleCode]-[Seq#]`      | `TC-LOGIN-003`        |
| Feature File           | `[module_name].feature`       | `login.feature`       |
| Cucumber Scenario      | Sentence describing behaviour | `Verify that valid credentials redirect the user to the Products page` |
| Page Object Class      | `[Page]Page.java`             | `LoginPage.java`      |
| Page Object Method     | `verb + Target`               | `clickLoginButton()`  |
| Bug Report             | `BUG-[ModuleCode]-[Seq#]`     | `BUG-LOGIN-001`       |

---

## 👨‍💻 Author

**AboSelimzzz**
[GitHub Profile](https://github.com/AboSelimzzz)