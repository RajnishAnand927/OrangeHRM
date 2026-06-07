# OrangeHRM Selenium Automation

This repository contains a Java-based UI automation framework for the OrangeHRM demo application using Selenium WebDriver, TestNG, and Maven. The project follows a Page Object Model structure so page behavior and test logic stay separated and easier to maintain.

## Tech Stack

- Java 21
- Maven
- Selenium WebDriver 4.43.0
- TestNG 7.12.0

## Project Structure

```text
src/
  main/java/com/example/
    action/      -> reusable WebDriver actions
    base/        -> browser setup and teardown
    pages/       -> page object classes
  test/java/com/example/testcases/
    LoginPageTestCases.java
    AdminPageTestCases.java
testng.xml       -> TestNG suite configuration
pom.xml          -> Maven dependencies and build config
```

## Current Test Coverage

### Login Flow

- Verifies the OrangeHRM login page title
- Verifies expected login page text
- Logs in with valid credentials
- Confirms the dashboard is displayed after login

### Admin Flow

- Logs in and navigates to the Admin page
- Verifies Admin page content
- Adds a new user through the Admin module

## Prerequisites

Before running the tests, make sure you have:

- Java 21 installed
- Maven installed and available in `PATH`
- A supported browser installed:
  - Chrome
  - Edge
  - Firefox
- The matching browser driver available to Selenium

The framework currently opens the browser directly with:

- `ChromeDriver`
- `EdgeDriver`
- `FirefoxDriver`

So your local machine needs a compatible browser/driver setup.

## Application Under Test

The suite is currently configured against the OrangeHRM demo site:

`https://opensource-demo.orangehrmlive.com/web/index.php/auth/login`

Default test credentials used in the code:

- Username: `Admin`
- Password: `admin123`

## How To Run

### Run from IDE

The most straightforward option is to run `testng.xml` directly from IntelliJ IDEA or Eclipse with TestNG support enabled.

### Run with Maven

Use the TestNG suite file:

```bash
mvn test -Dsurefire.suiteXmlFiles=testng.xml
```

## Browser Configuration

The suite reads these TestNG parameters from `testng.xml`:

- `browser`
- `url`

Example:

```xml
<parameter name="browser" value="chrome"/>
<parameter name="url" value="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"/>
```

You can switch the browser to `chrome`, `edge`, or `firefox`.

## Framework Notes

- Browser setup and teardown are managed in `BaseClass`
- Page navigation is handled through page object classes
- Shared Selenium helper methods are kept in `ActionDriver`
- The current `testng.xml` is set to run the Admin test flow
- A login suite is already present in `testng.xml` and can be re-enabled when needed

## Future Improvements

- Add explicit waits instead of relying mainly on implicit waits
- Externalize test data and credentials
- Add reporting and screenshots on failure
- Support parallel execution
- Integrate WebDriverManager for easier driver setup
- Expand negative and regression coverage

## Author

Rajnish Anand
