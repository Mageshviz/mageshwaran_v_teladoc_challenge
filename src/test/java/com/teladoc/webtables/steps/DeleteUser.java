package com.teladoc.webtables.steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertFalse;

public class DeleteUser {

    WebDriver driver;

    @Given("I am on the webtable pages")
    public void i_am_on_the_webtable_page() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://www.way2automation.com/angularjs-protractor/webtables/");
    }

    @When("I delete the user {string}")
    public void i_delete_the_user(String username) {
        // Locate the user row and click delete
        WebElement deleteButton = driver.findElement(By.xpath("//td[contains(text(),'" + username + "')]/following-sibling::td/button[@ng-click='delUser()']"));
        deleteButton.click();
        // Confirm the delete action
        WebElement confirmDelete = driver.findElement(By.xpath("//button[@ng-click='close(btn.result)' and contains(@class,'btn-primary')]"));
        confirmDelete.click();
    }

    @Then("I should not see the user {string} in the webtable")
    public void i_should_not_see_the_user_in_the_webtable(String username) {
        // Verify the user no longer exists in the table
        boolean isUserPresent = !driver.findElements(By.xpath("//td[contains(text(),'" + username + "')]")).isEmpty();
        assertFalse(isUserPresent);

        // Close the browser
        driver.quit();
    }
}
