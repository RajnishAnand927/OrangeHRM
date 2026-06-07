package com.example.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.example.base.BaseClass;
import com.example.pages.AdminPageClass;
import com.example.pages.DashboardPageClass;
import com.example.pages.LoginPageClass;

public class AdminPageTestCases extends BaseClass {
    AdminPageClass apc;
    DashboardPageClass dpc;
    LoginPageClass lpc;

    @Parameters({ "browser", "url" })
    @BeforeClass
    public void beforeMethod(String browser, String url) {
        setup(browser, url);

        lpc = new LoginPageClass();

        dpc = lpc.loginFunction("Admin", "admin123");
        apc = dpc.goToAdmin();
    }

    @Test(priority = 1)
    public void elementCheck() {
        Assert.assertTrue(apc.verifyText("Admin"), "Admin element not found!");
    }

    // @Test(priority = 2, groups = { "NegativeTest" })
    // public void negativeSearchEmpFunction() throws InterruptedException {
    // Assert.assertFalse(
    // apc.searchUserFunction(
    // "Admin",
    // "Admin",
    // "Aalim N",
    // "Enabled"),
    // "Search should fail for invalid employee");

    // }

    @Test(priority = 2, groups = { "Regression", "Positive Test" })
    public void addNewUser() throws InterruptedException {
        Assert.assertTrue(apc.addUserFunction("Admin", "A", "Enabled", "Admin123456",
                "abcd1234", "abcd1234"),
                "User added successfully");

    }

    // @AfterClass
    // public void endTest() {
    // tearDown();
    // }

}
