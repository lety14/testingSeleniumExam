package com.Pages;

import com.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.beans.Visibility;
import java.time.Duration;

/**
 * PageOpencart Class
 *
 * @author Leticia
 * @version 1.0
 */
public class PageOpencart extends BasePage {

    WebDriverWait wait = new WebDriverWait(_driver, Duration.ofMillis(1000));

    // Registration
    protected static final String myAccountXPath = "//span[normalize-space()='My Account']";
    protected static final String registerXPath = "//a[normalize-space()='Register']";
    protected static final String inputFirstNameID = "input-firstname";
    protected static final String inputLastNameID = "input-lastname";
    protected static final String inputEmailID = "input-email";
    protected static final String inputTelephoneID = "input-telephone";
    protected static final String inputPasswordID = "input-password";
    protected static final String inputPasswordConfirmID = "input-confirm";
    protected static final String newsletterNOXPath = "//input[@value='0']";
    protected static final String checkboxAgreePrivatePolicyXPath = "//input[@name='agree']";
    protected static final String buttonRegisterXPath = "//input[@value='Continue']";
    protected static final String successRegistrationMessageID = "content";
    // Search
    protected static final String inputSearchXPath = "//input[@placeholder='Search']";
    protected static final String buttonSearchXPath = "(//button[@class='btn btn-default btn-lg'])[1]";
    protected static final String addToChartXPath = "(//button[@type='button'])[9]";
    protected static final String productSuccessAddedXPath = "//div[@class='alert alert-success alert-dismissible']";


    /**
     * Method to send the register page
     */
    public void redirectToRegisterPage() {
        WebElement buttonMyAccount = getWebElement(By.xpath(myAccountXPath));
        wait.until(ExpectedConditions.elementToBeClickable(buttonMyAccount));
        buttonMyAccount.click();

        WebElement buttonRedirectToRegister = getWebElement(By.xpath(registerXPath));
        wait.until(ExpectedConditions.elementToBeClickable(buttonRedirectToRegister));
        buttonRedirectToRegister.click();
    }

    /***
     * Method to register a user
     * @param email String with an existing user's email
     * @param password String with the correct password for the user's email
     */
    public void fillLoginUserForm(String firstName, String lastName, String email, String telephone, String password, String passwordConfirm) {
        WebElement inputFirstName = getWebElement(By.id(inputFirstNameID));
        WebElement inputLastName = getWebElement(By.id(inputLastNameID));
        WebElement inputEmail = getWebElement(By.id(inputEmailID));
        WebElement inputTelephone = getWebElement(By.id(inputTelephoneID));
        WebElement inputPassword = getWebElement(By.id(inputPasswordID));
        WebElement inputPasswordConfirm = getWebElement(By.id(inputPasswordConfirmID));

        inputFirstName.clear();
        inputLastName.clear();
        inputEmail.clear();
        inputTelephone.clear();
        inputPassword.clear();
        inputPasswordConfirm.clear();

        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputEmail.sendKeys(email);
        inputTelephone.sendKeys(telephone);
        inputPassword.sendKeys(password);
        inputPasswordConfirm.sendKeys(passwordConfirm);

        wait.until(ExpectedConditions.textToBePresentInElementValue(inputFirstName, firstName));
        wait.until(ExpectedConditions.textToBePresentInElementValue(inputLastName, lastName));
        wait.until(ExpectedConditions.textToBePresentInElementValue(inputEmail, email));
        wait.until(ExpectedConditions.textToBePresentInElementValue(inputTelephone, telephone));
        wait.until(ExpectedConditions.textToBePresentInElementValue(inputPassword, password));
        wait.until(ExpectedConditions.textToBePresentInElementValue(inputPasswordConfirm, passwordConfirm));
    }

    /**
     * Method to select news options
     */
    public void selectNewsOptions() throws InterruptedException {

        WebElement newsletterNO = getWebElement(By.xpath(newsletterNOXPath));
        wait.until(ExpectedConditions.elementToBeClickable(newsletterNO));
        if (!newsletterNO.isSelected()) {
            newsletterNO.click();
        }
    }

    /**
     * Method to send the agree privacy politics
     */
    public void agreeProductPolicy() throws InterruptedException {

        WebElement checkboxPrivacyPolicy = getWebElement(By.xpath(checkboxAgreePrivatePolicyXPath));
        wait.until(ExpectedConditions.elementToBeClickable(checkboxPrivacyPolicy));
        if (!checkboxPrivacyPolicy.isSelected()) {
            checkboxPrivacyPolicy.click();
        }
    }

    /**
     * Method to send the registration request
     */
    public void registerUserRequest() throws InterruptedException {
        WebElement buttonRegister = getWebElement(By.xpath(buttonRegisterXPath));
        wait.until(ExpectedConditions.elementSelectionStateToBe((By.xpath(checkboxAgreePrivatePolicyXPath)), true));
        wait.until(ExpectedConditions.elementToBeClickable(buttonRegister));
        buttonRegister.click();
    }

    /**
     * Method to catch message of success registration
     *
     * @return String message of success registration
     */
    public String validateLoginSuccess() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(successRegistrationMessageID)));
        WebElement message = getWebElement(By.id(successRegistrationMessageID));
        return message.getText();
    }

    /**
     * Method to search a product
     *
     * @Param product
     */
    public void searchProduct(String product) {
        WebElement searchBox = getWebElement(By.xpath(inputSearchXPath));
        searchBox.clear();
        searchBox.sendKeys(product);
        wait.until(ExpectedConditions.textToBePresentInElementValue(searchBox, product));
        searchBox.sendKeys(Keys.ENTER);
    }

    /**
     * Method to click on button search
     */
    public void searchButtonClick() {
        WebElement search = getWebElement(By.xpath(buttonSearchXPath));
        wait.until(ExpectedConditions.elementToBeClickable(search));
        search.click();
    }

    /**
     * Method to add to chart a product
     */
    public void addToCart() {
        WebElement search = getWebElement(By.xpath(addToChartXPath));
        wait.until(ExpectedConditions.elementToBeClickable(search));
        search.click();
    }

    /**
     * Method to catch message of success product added to cart
     *
     * @return String message of success
     */
    public String validateProductAddedShoppingCartSuccess() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(productSuccessAddedXPath)));
        WebElement message = getWebElement(By.xpath(productSuccessAddedXPath));
        return message.getText();
    }
}
