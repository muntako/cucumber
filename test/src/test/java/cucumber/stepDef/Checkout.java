package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Checkout {

  WebDriver driver;

  String baseUrl = "https://www.saucedemo.com/";
  String username = "standard_user";
  String password = "secret_sauce";
  int count = 2;
  List<WebElement> elements;
  List<WebElement> removeButtons;

  @Given("user is on cart detail page")
  public void user_is_on_saucedemo_cart_detail_page() {
    WebDriverManager.chromedriver().setup();
    ChromeOptions opt = new ChromeOptions();
    opt.setHeadless(false);

    driver = new ChromeDriver(opt);
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    driver.manage().window().maximize();

    driver.get(baseUrl);
    driver.findElement(By.id("user-name")).sendKeys(username);
    driver.findElement(By.id("password")).sendKeys(password);
    driver.findElement(By.id("login-button")).click();

    elements =
      driver.findElements(
        By.xpath(
          "//button[@class=\"btn btn_primary btn_small btn_inventory \"]"
        )
      );

    for (int j = 0; j < count; j++) {
      elements.get(j).click();
    }

    driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

    String targetedUrl = "https://www.saucedemo.com/cart.html";

    String currentUrl = driver.getCurrentUrl();

    Assert.assertEquals(currentUrl, targetedUrl);
  }

  @When("user click checkout button")
  public void user_click_checkout_button() {
    driver.findElement(By.xpath("//button[@id='checkout']")).click();
  }

  @Then("user redirect to checkout page")
  public void user_redirect_to_cart_page_detail(){
    String targetedUrl = "https://www.saucedemo.com/checkout-step-one.html";
    String currentUrl = driver.getCurrentUrl();
    Assert.assertEquals(currentUrl, targetedUrl);
  }

  @When("user input (.*) as first name$")
  public void user_input_first_name(String firstName){
    driver.findElement(By.id("first-name")).sendKeys(firstName);
    String firstNameValue = driver
      .findElement(By.id("first-name"))
      .getAttribute("value");
    Assert.assertEquals(firstName, firstNameValue);
  }

  @When("user input (.*) as last name$")
  public void user_input_last_name(String lastName){
    driver.findElement(By.id("last-name")).sendKeys(lastName);
    String lastNameValue = driver
      .findElement(By.id("last-name"))
      .getAttribute("value");
    Assert.assertEquals(lastName, lastNameValue);
  }

  @When("user input (.*) as postal code$")
  public void user_input_posatl_code(String postalCode){
    driver.findElement(By.id("postal-code")).sendKeys(postalCode);
    String postalCodeValue = driver
      .findElement(By.id("postal-code"))
      .getAttribute("value");
    Assert.assertEquals(postalCodeValue, postalCode);
  }

  @When("user click continue button")
  public void user_click_continue_button() {
    driver.findElement(By.xpath("//input[@id='continue']")).click();
  }

  @Then("user get checkout error message")
  public void user_get_error_message() {
    WebElement element = driver.findElement(By.xpath("//button[@class='error-button']"));
    Assert.assertNotNull(element);
    driver.close();
    driver.quit();
  }

  @Then("user redirect to checkout overview page")
  public void user_redirect_to_order_overview_page() {
    String targetedUrl = "https://www.saucedemo.com/checkout-step-two.html";
    String currentUrl = driver.getCurrentUrl();
    Assert.assertEquals(currentUrl, targetedUrl);
  }

  @When("user click finish button")
  public void user_click_finish_button() {
    driver.findElement(By.xpath("//button[@id='finish']")).click();
  }

  @Then("checkout is completed")
  public void checkout_is_completed(){
    String targetedUrl = "https://www.saucedemo.com/checkout-complete.html";
    String currentUrl = driver.getCurrentUrl();
    Assert.assertEquals(currentUrl, targetedUrl);

    String header = driver.findElement(By.xpath("//span[@class='title']")).getText();
    Assert.assertEquals(header, "Checkout: Complete!");

    driver.close();
    driver.quit();
  }
}
