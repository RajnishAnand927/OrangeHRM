package com.example.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.action.ActionDriver;
import com.example.base.BaseClass;

public class AdminPageClass extends BaseClass {
    @FindBy(xpath = "//h6[contains(.,'Admin')]")
    WebElement adminEle;

    @FindBy(xpath = "//label[text()='Username']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement username;

    @FindBy(xpath = "//label[text()='User Role']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text')]")
    WebElement userroleBtn;

    @FindBy(xpath = "//div[@role='listbox']//span")
    List<WebElement> selectRoll;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement empname;

    @FindBy(xpath = "//label[text()='Status']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text')]")
    WebElement statusBtn;

    @FindBy(xpath = "//label[text()='Status']/ancestor::div[contains(@class,'oxd-input-group')]//div[@role='listbox']//span")
    List<WebElement> selectStatus;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitBtn;

    @FindBy(xpath = "(//div[@role='listbox']//span)[1]")
    WebElement firstSuggest;

    @FindBy(xpath = "//span[text()='Invalid']")
    WebElement invalidMsg;

    @FindBy(xpath = "//button[contains(.,'Add')]")
    WebElement addBtn;

    @FindBy(xpath = "//label[text()='Password']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement passWordField;

    @FindBy(xpath = "//label[text()='Confirm Password']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    WebElement confirmPassWordField;

    @FindBy(xpath = "//button[contains(.,'Save')]")
    WebElement saveBtn;

    @FindBy(xpath = "//button[contains(.,'Cancel')]")
    WebElement cancelBtn;

    By firstSuggestion = By.xpath("(//div[contains(@class,'oxd-autocomplete-option')])[1]");

    By noRecords = By.xpath("//span[contains(.,'No Records Found')]");

    By successToast = By.xpath("//div[contains(@class,'oxd-toast-content')]//p[contains(.,'Successfully Saved')]");

    public AdminPageClass() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean verifyText(String expText) {
        return expText.equals(ActionDriver.fetchEleText(adminEle));
    }

    public boolean searchUserFunction(String usernametxt, String userroletxt, String empNametxt, String statustxt)
            throws InterruptedException {
        if (!ActionDriver.inputData(username, usernametxt))// Inputs username in username field
            return false;
        if (!ActionDriver.buttonAction(userroleBtn))// clicks on userrole option box
            return false;
        if (!ActionDriver.optionWithoutSelectAction(selectRoll, userroletxt))// selects option defined by user
            return false;
        if (!ActionDriver.inputData(empname, empNametxt))// inputs data to employee name field
            return false;

        Thread.sleep(2000);// waith for 2 sec for result to load for employee name

        // if employee name doesnt exist or is invalid throws msg
        if (ActionDriver.isElementPresent(invalidMsg)) {
            System.out.println("Employee Doesn't Exist");
            return false;
        }
        // stores all the search result of employee name in option list
        List<WebElement> options = getDriver()
                .findElements(By.xpath("//div[contains(@class,'oxd-autocomplete-option')]"));
        if (options.isEmpty()) {
            System.out.println("Employee Doesn't Exist");
            return false;
        }
        // check no record found is no employee was found in search result
        String optionText = options.get(0).getText().trim();
        if (optionText.equals("No Records Found")) {
            System.out.println("Employee Doesn't Exist");
            return false;
        }
        // clicks the first option from the result of search list
        if (!ActionDriver.buttonAction(options.get(0)))
            return false;
        // click on status option
        if (!ActionDriver.buttonAction(statusBtn))
            return false;
        // selects options defined by user "Enabled" or "Disabled"
        if (!ActionDriver.optionWithoutSelectAction(selectStatus, statustxt))
            return false;
        // clicks submit button to show result
        return ActionDriver.buttonAction(submitBtn);
    }

    public boolean addUserFunction(String userroletxt, String empNametxt, String statustxt, String usernametxt,
            String passwordtxt, String confirmpasstxt)
            throws InterruptedException {
        ActionDriver.buttonAction(addBtn);
        if (!ActionDriver.buttonAction(userroleBtn))// clicks on userrole option box
            return false;
        if (!ActionDriver.optionWithoutSelectAction(selectRoll, userroletxt))// selects option defined by user
            return false;
        if (!ActionDriver.inputData(empname, empNametxt))// inputs data to employee name field
            return false;

        Thread.sleep(2000);// waith for 2 sec for result to load for employee name
        List<WebElement> options = getDriver()
                .findElements(By.xpath("//div[contains(@class,'oxd-autocomplete-option')]"));
        if (options.isEmpty()) {
            System.out.println("Employee Doesn't Exist");
            return false;
        }
        if (!ActionDriver.buttonAction(options.get(0)))
            return false;
        if (!ActionDriver.buttonAction(statusBtn))
            return false;
        // selects options defined by user "Enabled" or "Disabled"
        if (!ActionDriver.optionWithoutSelectAction(selectStatus, statustxt))
            return false;
        // Inputs username in username field
        if (!ActionDriver.inputData(username, usernametxt))
            return false;
        if (!ActionDriver.inputData(passWordField, passwordtxt))
            return false;
        if (!ActionDriver.inputData(confirmPassWordField, confirmpasstxt))
            return false;

        ActionDriver.buttonAction(saveBtn);
        return ActionDriver.waitForText(successToast, "Successfully Saved");

    }

    public boolean verifyAddUserValidationForBlankForm() {
        ActionDriver.buttonAction(addBtn);
        ActionDriver.buttonAction(saveBtn);
        return getDriver().findElements(By.xpath("//span[text()='Required']")).size() >= 4;

    }

}
