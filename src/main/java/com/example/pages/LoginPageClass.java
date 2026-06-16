package com.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.action.ActionDriver;
import com.example.base.BaseClass;

public class LoginPageClass extends BaseClass {

    @FindBy(xpath = "//p[contains(.,'Username : Admin')]")
    WebElement usernameAdminEle;

    @FindBy(name = "username")
    WebElement username;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement logInBtn;

    @FindBy(xpath = "//p[contains(@class,'oxd-alert-content-text')]")
    WebElement invalidCredentialsMsg;

    @FindBy(xpath = "//span[text()='Required']")
    java.util.List<WebElement> requiredMessages;

    public LoginPageClass() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean verifyTitle(String expTitle) {
        return expTitle.equals(ActionDriver.fetchTitle());
    }

    public boolean verifyText(String expText) {
        return expText.equals(ActionDriver.fetchEleText(usernameAdminEle));
    }

    public DashboardPageClass loginFunction(String email, String pwd) {
        boolean status1 = ActionDriver.inputData(username, email);
        boolean status2 = ActionDriver.inputData(password, pwd);

        if (status1 && status2) {
            ActionDriver.buttonAction(logInBtn);
        } else {
            System.out.println("Username or password input failed");
        }
        return new DashboardPageClass();
    }

    public void submitLogin(String email, String pwd) {
        ActionDriver.inputData(username, email);
        ActionDriver.inputData(password, pwd);
        ActionDriver.buttonAction(logInBtn);
    }

    public boolean verifyInvalidCredentialsMessage() {
        return "Invalid credentials".equals(ActionDriver.fetchEleText(invalidCredentialsMsg));
    }

    public boolean verifyRequiredMessagesCount(int expectedCount) {
        return requiredMessages.size() == expectedCount;
    }

}
