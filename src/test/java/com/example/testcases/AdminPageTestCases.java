package com.example.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(String browser, String url) {
        setup(browser, url);

        lpc = new LoginPageClass();

        dpc = lpc.loginFunction("Admin", "admin123");
        apc = dpc.goToAdmin();
    }

    @Test(priority = 1, groups = { "smoke", "regression", "valid" }, description = "Verifies Admin page is opened")
    public void elementCheck() {
        Assert.assertTrue(apc.verifyText("Admin"), "Admin element not found!");
    }

    @Test(priority = 2, groups = { "regression", "valid" }, description = "Searches an existing admin user")
    public void validSearchUserFunction() throws InterruptedException {
        Assert.assertTrue(apc.searchUserFunction("Admin", "Admin", "A", "Enabled"),
                "Search should complete for an existing admin user");
    }

    @Test(priority = 3, groups = { "regression", "invalid" }, description = "Rejects search with an invalid employee name")
    public void invalidSearchEmpFunction() throws InterruptedException {
        Assert.assertFalse(apc.searchUserFunction("Admin", "Admin", "Invalid Employee Name", "Enabled"),
                "Search should fail for invalid employee");

    }

    @Test(priority = 4, groups = { "regression", "valid" }, description = "Adds a new admin user with valid data")
    public void addNewUser() throws InterruptedException {
        String username = "AutoUser" + System.currentTimeMillis();
        Assert.assertTrue(apc.addUserFunction("Admin", "A", "Enabled", username,
                "abcd1234", "abcd1234"),
                "User added successfully");

    }

    @Test(priority = 5, groups = { "regression", "invalid" }, description = "Shows validation when saving a blank Add User form")
    public void blankAddUserValidationCheck() {
        Assert.assertTrue(apc.verifyAddUserValidationForBlankForm(), "Required validation messages not displayed");
    }

    @AfterMethod(alwaysRun = true)
    public void endTest() {
        tearDown();
    }

}
