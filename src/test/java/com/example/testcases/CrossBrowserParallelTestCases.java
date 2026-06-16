package com.example.testcases;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.base.BaseClass;
import com.example.pages.DashboardPageClass;
import com.example.pages.LoginPageClass;

public class CrossBrowserParallelTestCases extends BaseClass {

    @DataProvider(name = "parallelBrowsers", parallel = true)
    public Object[][] parallelBrowsers(ITestContext context) {
        String url = context.getCurrentXmlTest().getParameter("url");
        return new Object[][] {
                { "chrome", url },
                { "edge", url }
        };
    }

    @Test(dataProvider = "parallelBrowsers", groups = { "parallel", "cross-browser", "smoke",
            "valid" }, description = "Runs the valid login test in parallel on Chrome and Edge")
    public void validLoginRunsInChromeAndEdge(String browser, String url) {
        try {
            setup(browser, url);
            System.out.println("Browser: " + browser + " | Thread ID: " + Thread.currentThread().getId());

            LoginPageClass loginPage = new LoginPageClass();
            DashboardPageClass dashboard = loginPage.loginFunction("Admin", "admin123");

            Assert.assertTrue(dashboard.verifyText("Dashboard"),
                    "Dashboard not displayed after login on " + browser);
        } finally {
            tearDown();
        }
    }
}
