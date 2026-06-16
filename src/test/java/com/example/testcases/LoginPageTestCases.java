package com.example.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.example.base.BaseClass;
import com.example.pages.DashboardPageClass;
import com.example.pages.LoginPageClass;

public class LoginPageTestCases extends BaseClass {
    DashboardPageClass dsbc;
    LoginPageClass loginpg;

    @Parameters({ "browser", "url" })

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(String browser, String url) {
        setup(browser, url);

        loginpg = new LoginPageClass();
    }

    @Test(priority = 1, groups = { "smoke", "regression", "valid" }, description = "Verifies the page title on login page")
    public void titleCheck() {
        Assert.assertTrue(loginpg.verifyTitle("OrangeHRM"), "Title Not Matched");

    }

    @Test(priority = 2, groups = { "smoke", "regression", "valid" }, description = "Verify the username hint is displayed")
    public void elementCheck() {
        Assert.assertTrue(loginpg.verifyText("Username : Admin"), "Text Not Matched");
    }

    @Test(priority = 3, groups = { "smoke", "regression", "valid" }, description = "Logs in with valid credentials")
    public void loginFunctionCheck() {
        dsbc = loginpg.loginFunction("Admin", "admin123");
        Assert.assertTrue(dsbc.verifyText("Dashboard"), "Dashboard not displayed after login");
    }

    @Test(priority = 4, groups = { "regression", "invalid" }, description = "Shows invalid credentials for wrong password")
    public void invalidPasswordLoginCheck() {
        loginpg.submitLogin("Admin", "wrongPassword");
        Assert.assertTrue(loginpg.verifyInvalidCredentialsMessage(), "Invalid credentials message not displayed");
    }

    @Test(priority = 5, groups = { "regression", "invalid" }, description = "Shows required validation when login form is blank")
    public void blankLoginValidationCheck() {
        loginpg.submitLogin("", "");
        Assert.assertTrue(loginpg.verifyRequiredMessagesCount(2), "Required validation messages not displayed");
    }

    @AfterMethod(alwaysRun = true)
    public void endTest() {
        tearDown();
    }

}
