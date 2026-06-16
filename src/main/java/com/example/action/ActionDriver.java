package com.example.action;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.base.BaseClass;

public class ActionDriver extends BaseClass {

    public static WebElement waitForVisibility(
            WebElement ele) {
        WebDriverWait wait = new WebDriverWait(
                getDriver(),
                Duration.ofSeconds(10));
        return wait.until(
                ExpectedConditions.visibilityOf(ele));
    }

    public static WebElement waitForClickable(
            WebElement ele) {
        WebDriverWait wait = new WebDriverWait(
                getDriver(),
                Duration.ofSeconds(10));
        return wait.until(
                ExpectedConditions.elementToBeClickable(ele));
    }

    public static String fetchTitle() {
        if (getDriver() != null)
            return getDriver().getTitle();
        else
            return null;
    }

    public static String fetchEleText(WebElement ele) {
        if (ele != null) {
            return waitForVisibility(ele).getText();
        }
        return null;
    }

    public static boolean inputData(WebElement ele, String text) {
        if (ele != null) {
            if (ele.isDisplayed() && ele.isEnabled()) {
                waitForVisibility(ele).sendKeys(text);
                return true;
            }
        }
        return false;
    }

    public static boolean buttonAction(WebElement ele) {

        if (ele != null) {
            String type = ele.getAttribute("type");
            if ("submit".equals(type)) {
                waitForClickable(ele).submit();
            } else {
                waitForClickable(ele).click();
            }
            return true;
        }
        return false;
    }

    public static boolean optionAction(WebElement ele, int index) {
        if (ele != null) {
            Select options = new Select(ele);
            List<WebElement> allOptions = options.getOptions();
            for (WebElement option : allOptions) {
                System.out.println(option.getText());
            }
            options.selectByIndex(index);
            return true;

        }
        return false;
    }

    public static boolean optionWithoutSelectAction(List<WebElement> ele, String selectedOption) {
        if (ele != null) {
            for (WebElement option : ele) {
                System.out.println(option.getText());
            }
            for (WebElement option : ele) {
                if (option.getText().trim().equals(selectedOption)) {
                    waitForClickable(option).click();
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isElementPresent(
            By locator) {

        try {
            getDriver().findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isElementPresent(
            WebElement locator) {

        try {
            locator.isDisplayed();
            System.out.println("Element Exists");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static WebElement waitForVisibility(
            By locator) {

        WebDriverWait wait = new WebDriverWait(
                getDriver(),
                Duration.ofSeconds(10));

        return wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(
                                locator));
    }

    public static boolean waitForText(
            By locator,
            String expectedText) {

        try {
            WebDriverWait wait = new WebDriverWait(
                    getDriver(),
                    Duration.ofSeconds(10));

            return wait.until(
                    ExpectedConditions
                            .textToBePresentInElementLocated(
                                    locator,
                                    expectedText));
        } catch (Exception e) {
            return false;
        }
    }

}
