package com.example.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.example.base.BaseClass;
import com.example.pages.DashboardPageClass;
import com.example.pages.LoginPageClass;

public class ParallelExecutionTestCases extends BaseClass {
    LoginPageClass loginpg;

    @Parameters({ "browser", "url" })
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(String browser, String url) {
        setup(browser, url);
        loginpg = new LoginPageClass();
    }

    @Test(groups = { "parallel", "smoke", "valid" }, description = "Parallel test case one logs in on its own browser thread")
    public void parallelLoginOne() {
        System.out.println("parallelLoginOne Thread ID: " + Thread.currentThread().getId());
        DashboardPageClass dashboard = loginpg.loginFunction("Admin", "admin123");
        Assert.assertTrue(dashboard.verifyText("Dashboard"), "Dashboard not displayed in parallelLoginOne");
    }

    @Test(groups = { "parallel", "regression", "invalid" }, description = "Parallel test case two validates invalid login on its own browser thread")
    public void parallelInvalidLoginTwo() {
        System.out.println("parallelInvalidLoginTwo Thread ID: " + Thread.currentThread().getId());
        loginpg.submitLogin("Admin", "wrongPassword");
        Assert.assertTrue(loginpg.verifyInvalidCredentialsMessage(),
                "Invalid credentials message not displayed in parallelInvalidLoginTwo");
    }

    @AfterMethod(alwaysRun = true)
    public void endTest() {
        tearDown();
    }
}
