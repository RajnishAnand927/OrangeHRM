package com.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.action.ActionDriver;
import com.example.base.BaseClass;

public class DashboardPageClass extends BaseClass {
    @FindBy(xpath = "//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module']")
    WebElement dashboardHeader;

    @FindBy(xpath = "//a[@href='/web/index.php/admin/viewAdminModule']")
    WebElement adminBtn;

    public DashboardPageClass() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean verifyTitle(String expTitle) {
        return expTitle.equals(ActionDriver.fetchTitle());
    }

    public boolean verifyText(String expText) {

        String actualText = ActionDriver.fetchEleText(dashboardHeader);

        return actualText != null &&
                actualText.strip()
                        .equals(expText.strip());
    }

    public AdminPageClass goToAdmin() {
        ActionDriver.buttonAction(adminBtn);
        return new AdminPageClass();
    }

}
