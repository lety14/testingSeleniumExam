package com.Test;

import com.Base.BasePage;
import com.Pages.PageOpencart;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.extentReports.ExtentFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;


/**
 * Test Class
 *
 *  @author Leticia
 *  @version 1.0
 */
public class Test {
    private static WebDriver driver;
    static ExtentSparkReporter spark = new ExtentSparkReporter("./target/Spark.html");
    static ExtentReports extent;
    ExtentTest test;

    /**
     * Method to initialize the driver before run the tests
     */
    @BeforeAll
    static void setUp() {
        BasePage _basePage = new BasePage();
        _basePage.openApp();
        driver = _basePage.getDriver();
        extent = ExtentFactory.getInstance();
        extent.attachReporter(spark);
    }


    @org.junit.jupiter.api.Test
    @Tag("smoke")
    @DisplayName("Registration, search and add to cart process test.")
    public void testLoginAndAddToCart() throws InterruptedException {
        test = extent.createTest("Registration, search and add to cart process test.");
        test.log(Status.INFO, "Test Started!");
        PageOpencart page = new PageOpencart();
        page.redirectToRegisterPage();
        test.log(Status.INFO, "1.- Test Registration!");
        test.log(Status.PASS, "Open Registration Page");
        page.fillLoginUserForm("Erica", "Green", "Green233@google.net", "16954418157",
                "Hola*123123123", "Hola*123123123");
        test.log(Status.PASS, "Add Registration Information");
        page.selectNewsOptions();
        test.log(Status.PASS, "Select NO option registration");
        page.agreeProductPolicy();
        test.log(Status.PASS, "Agree Product Policy");
        page.registerUserRequest();
        test.log(Status.PASS, "Send Request To Register New User");
        String result = page.validateLoginSuccess();
        assertTrue(result.contains("Congratulations! Your new account has been successfully created!"));
        test.log(Status.PASS, "Check Successfully Registration");
        test.log(Status.INFO, "2.- Test Search and Add to Cart!");
        page.searchProduct("iphone");
        test.log(Status.PASS, "Complete input with product");
        page.searchButtonClick();
        test.log(Status.PASS, "Click search input");
        page.addToCart();
        test.log(Status.PASS, "Add product to cart clicked");
        String messageProductAdded = page.validateProductAddedShoppingCartSuccess();
        assertTrue(messageProductAdded.contains("Success: You have added"));
        test.log(Status.PASS, "The product had been added successfully");
        test.log(Status.INFO, "Test finish successfully");
    }


    /**
     * Method to close the driver after the test ends
     */
    @AfterAll
    static void tearDown() {
        extent.flush();
        driver.quit();
    }
}
