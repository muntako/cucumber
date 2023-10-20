package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Login {

  WebDriver driver;

  String baseUrl = "https://www.saucedemo.com/";

  @Given("user is on Saucedemo login page")
  public void user_is_on_saucedemo_login_page() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions opt = new ChromeOptions();
    opt.setHeadless(false);

    driver = new ChromeDriver(opt);
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    driver.manage().window().maximize();

    driver.get(baseUrl);
    Assert.assertEquals(baseUrl, driver.getCurrentUrl());
  }

  @When("user input (.*) as username$")
  public void user_input_username(String username) {
    driver.findElement(By.id("user-name")).sendKeys(username);
    String usernameValue = driver
      .findElement(By.id("user-name"))
      .getAttribute("value");
    Assert.assertEquals(username, usernameValue);
  }

  @When("user input (.*) as password$")
  public void user_input_password(String password) {
    driver.findElement(By.id("password")).sendKeys(password);
    String passwordValue = driver
      .findElement(By.id("password"))
      .getAttribute("value");
    Assert.assertEquals(password, passwordValue);
  }

  @And("user click login button")
  public void user_click_login_button() {
    driver.findElement(By.id("login-button")).click();
  }

  @Then("user redirect to dashboard")
  public void user_redirect_to_dashboard() {
    String targetedUrl = "https://www.saucedemo.com/inventory.html";

    String dashbordUrl = driver.getCurrentUrl();

    Assert.assertEquals(dashbordUrl, targetedUrl);
    driver.close();
    driver.quit();
  }

  @Then("user get error message")
  public void user_get_error_message(){
    boolean isErrorMessageShow = driver.getPageSource().contains("Epic sadface: Username and password do not match any user in this service");
    Assert.assertTrue(isErrorMessageShow);
    driver.close();
    driver.quit();
  }
}
