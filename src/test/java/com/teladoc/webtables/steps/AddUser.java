package com.teladoc.webtables.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;
import java.util.Map;


public class AddUser {

    WebDriver driver;

    // Initialize ChromeDriver
    @Given("I am on the webtable page")
    public void i_am_on_the_webtable_page() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("http://www.way2automation.com/angularjs-protractor/webtables/");

    }

    @When("I add a user with the following details:")
    public void i_add_a_user_with_the_following_details(DataTable dataTable) {
        // Click on Add User Button
        driver.findElement(By.xpath("//button[@type='add']")).click();

        // Extract data from DataTable
        List<Map<String, String>> userDetails = dataTable.asMaps(String.class, String.class);
        Map<String, String> user = userDetails.getFirst();

        // Fill in the user details
        driver.findElement(By.name("FirstName")).sendKeys(user.get("First Name"));
        driver.findElement(By.name("LastName")).sendKeys(user.get("Last Name"));
        driver.findElement(By.name("UserName")).sendKeys(user.get("User Name"));
        driver.findElement(By.name("Email")).sendKeys(user.get("Email"));
        driver.findElement(By.name("Mobilephone")).sendKeys(user.get("Cell Phone"));

        // Select role (you'll need to modify this depending on the selector type)
        WebElement roleDropdown = driver.findElement(By.name("RoleId"));
            roleDropdown.findElement(By.xpath("//option[text()='" + user.get("Role") + "']")).click();

            // Click Save button
            driver.findElement(By.xpath("//button[text()='Save']")).click();
        }

        @Then("I should see the user {string} in the webtable")
    public void i_should_see_the_user_in_the_webtable(String username) {
       // Locate the webtable
        WebElement table = driver.findElement(By.cssSelector(".smart-table"));
        List<WebElement> rows = table.findElements(By.cssSelector("tbody tr"));

        boolean userFound = false;
        for (WebElement row : rows) {
            if (row.getText().contains(username)) {
                userFound = true;
                break;
            }
        }

        // Assert that the user is found
        Assert.assertTrue("User " + username + " not found in the table", userFound);

        // Close the browser
        driver.quit();
         }
}

