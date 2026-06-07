package com.example.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.example.base.BaseClass;
import com.example.pages.DashboardPageClass;
import com.example.pages.LoginPageClass;

public class LoginPageTestCases extends BaseClass {
    DashboardPageClass dsbc;
    LoginPageClass loginpg;

    @Parameters({ "browser", "url" })

    @BeforeClass
    public void beforeMethod(String browser, String url) {
        setup(browser, url);

        loginpg = new LoginPageClass();
    }

    @Test(priority = 1, description = "Verifies the page title on login page")
    public void titleCheck() {
        Assert.assertTrue(loginpg.verifyTitle("OrangeHRM"), "Title Not Matched");

    }

    @Test(priority = 2, dependsOnMethods = "titleCheck", description = "Verify the element 'Login' is displayed and not null")
    public void elementCheck() {
        Assert.assertTrue(loginpg.verifyText("Username : Admin"), "Text Not Matched");
    }

    @Test(priority = 3, dependsOnMethods = "elementCheck", description = "sends data to email and password field and does dubmit button click")
    public void loginFunctionCheck() {
        dsbc = loginpg.loginFunction("Admin", "admin123");
        Assert.assertTrue(dsbc.verifyText("Dashboard"), "Dashboard not displayed after login");
    }

    @AfterClass
    public void endTest() {
        tearDown();
    }

}
