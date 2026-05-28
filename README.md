# 🧪 SauceDemo — Selenium Java BDD Framework

![Java](https://img.shields.io/badge/Java-25-orange?logo=java)
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

- [About the Project](#about-the-project)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [How to Run Tests](#how-to-run-tests)
- [Test Users](#test-users)
- [Sprint Coverage](#sprint-coverage)
- [Branching Strategy](#branching-strategy)
- [Naming Conventions](#naming-conventions)

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
- **Screenshot on Failure** — auto-captured and embedded in reports

---

## 🛠️ Tech Stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 25 | Programming language |
| Selenium WebDriver | 4.43.0 | Browser automation |
| Cucumber | 7.34.3 | BDD framework |
| JUnit | 4 | Test runner |
| Log4j2 | 2.25.4 | Logging |
| Maven | Latest | Build & dependency management |

---

## 📁 Project Structure

```
saucedemo-selenium-java-bdd/
├── resources/
│   └── screenshots/                  ← Runtime failure screenshots
├── src/
│   ├── main/
│   │   ├── java/com/example/framework/
│   │   │   ├── config/
│   │   │   │   └── ConfigReader.java
│   │   │   ├── driver/
│   │   │   │   └── DriverFactory.java
│   │   │   ├── pages/
│   │   │   │   ├── BasePage.java
│   │   │   │   └── LoginPage.java
│   │   │   └── utils/
│   │   │       ├── LoggerUtil.java
│   │   │       ├── ScreenshotUtils.java
│   │   │       ├── TestUtils.java
│   │   │       └── WaitUtils.java
│   │   └── resources/
│   │       ├── config.properties
│   │       └── log4j2.xml
│   └── test/
│       ├── java/com/example/framework/
│       │   ├── features/
│       │   │   └── authentication/
│       │   │       └── login.feature
│       │   ├── stepDefinitions/
│       │   │   ├── api/
│       │   │   └── web/
│       │   │       ├── CommonSteps.java
│       │   │       └── LoginSteps.java
│       │   ├── support/
│       │   │   └── Hooks.java
│       │   └── runner/
│       │       └── TestRunner.java
│       └── resources/
├── .gitignore
├── pom.xml
└── README.md
```

---

## ✅ Prerequisites

Make sure you have the following installed before running the framework:

- [Java JDK 25+](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3.8+](https://maven.apache.org/download.cgi)
- [Google Chrome](https://www.google.com/chrome/) / Firefox / Edge
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
validUsername=standard_user
password=secret_sauce
implicitWait=10
explicitWait=15
```

---

## ▶️ How to Run Tests

**Run all tests**
```bash
mvn test
```

**Run by tag**
```bash
# Run smoke tests only
mvn test -Dcucumber.filter.tags="@smoke"

# Run a specific sprint
mvn test -Dcucumber.filter.tags="@sprint1"

# Run multiple tags
mvn test -Dcucumber.filter.tags="@smoke and @login"
```

**Run with a specific browser**
```bash
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

**Re-run only failed tests**
```bash
mvn test -Dcucumber.features="@target/cucumber-reports/rerun.txt"
```

---

## 👤 Test Users

SauceDemo provides built-in test accounts for different test scenarios:

| Username | Password | Behavior |
|---|---|---|
| `standard_user` | `secret_sauce` | ✅ Full normal flow |
| `locked_out_user` | `secret_sauce` | 🔒 Login blocked |
| `problem_user` | `secret_sauce` | 🐛 UI bugs present |
| `performance_glitch_user` | `secret_sauce` | 🐢 Slow response |
| `error_user` | `secret_sauce` | ❌ Some interactions fail |
| `visual_user` | `secret_sauce` | 👁️ Visual/CSS bugs |

---

## 📊 Sprint Coverage

| Sprint | Feature Area | Tag | Status |
|---|---|---|---|
| Sprint 1 | Authentication & Login | `@sprint1` | 🚧 In Progress |
| Sprint 2 | Product Catalog | `@sprint2` | ⏳ Pending |
| Sprint 3 | Shopping Cart | `@sprint3` | ⏳ Pending |
| Sprint 4 | Checkout Flow | `@sprint4` | ⏳ Pending |
| Sprint 5 | Navigation & Edge Cases | `@sprint5` | ⏳ Pending |

---

## 🌿 Branching Strategy

```
main          ← stable framework setup + final release
└── develop   ← collects completed sprints
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

| Artifact | Format | Example |
|---|---|---|
| User Story | `US-[Sprint#]-[Story#]` | `US-01-03` |
| Functional Requirement | `FR-[Module#]-[Req#]` | `FR-01-04` |
| Test Case | `TC-[ModuleCode]-[Seq#]` | `TC-LOGIN-003` |
| Feature File | `[module_name].feature` | `login.feature` |
| Cucumber Scenario | `action_condition_expectedResult` | `login_valid_credentials_redirects_to_products` |
| Page Object Method | `verb + Element` | `clickLoginButton()` |
| Bug Report | `BUG-[ModuleCode]-[Seq#]` | `BUG-LOGIN-001` |

---

## 👨‍💻 Author

**AboSelimzzz**
[GitHub Profile](https://github.com/AboSelimzzz)