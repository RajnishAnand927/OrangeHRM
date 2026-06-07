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

    public LoginPageClass() {
        PageFactory.initElements(driver, this);
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

}
