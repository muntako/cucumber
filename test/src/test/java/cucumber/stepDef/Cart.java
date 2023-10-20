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

public class Cart {

  WebDriver driver;

  String baseUrl = "https://www.saucedemo.com/";
  String username = "standard_user";
  String password = "secret_sauce";
  List<WebElement> elements;
  List<WebElement> removeButtons;

  @Given("user is on Saucedemo dashboard page")
  public void user_is_on_saucedemo_dashboard_page() {
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

    String targetedUrl = "https://www.saucedemo.com/inventory.html";

    String dashbordUrl = driver.getCurrentUrl();

    Assert.assertEquals(dashbordUrl, targetedUrl);
  }

  @When("user views inventories")
  public void user_view_items() {
    elements = driver.findElements(By.xpath("//button[@class=\"btn btn_primary btn_small btn_inventory \"]"));
    Assert.assertTrue(elements.size() > 0);
  }

  @When("user click button add to cart (.*) time/times$")
  public void user_click_add_to_cart(int count) {
    for (int j = 0; j < count; j++) {
      elements.get(j).click();
    }
  }

  @Then("cart badge label value is (.*)$")
  public void cart_badge_value_change(String count){
    String badgeLabel =  driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
    Assert.assertTrue(badgeLabel.equals(count));
  }

  @When("user click shopping cart icon")
  public void user_click_shopping_cart_icon(){
     driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
  }

  @Then("user redirect to cart detail page")
  public void user_redirect_to_cart_page_detail(){
    String targetedUrl = "https://www.saucedemo.com/cart.html";

    String currentUrl = driver.getCurrentUrl();

    Assert.assertEquals(currentUrl, targetedUrl);
  }

  @And("item count in cart detail page is (.*)$")
  public void count_item_in_cart_detail_page(int count){
    int itemCount = driver.findElements(By.xpath("//div[@class='cart_item']")).size();
    Assert.assertEquals(count, itemCount);
  }

  @When("user click remove button (.*) time/times$")
  public void user_click_remove_button(int count){
    removeButtons  = driver.findElements(By.xpath("//button[@class='btn btn_secondary btn_small cart_button']"));

    for (int i = 0; i < count; i++) {
      removeButtons.get(i).click();
    }
  }

  
  @When("user click continue shopping button")
  public void user_click_continue_shopping_button(){
     driver.findElement(By.xpath("//button[@id='continue-shopping']")).click();
  }

  @Then("user back to dashboard page")
  public void user_back_to_dashboard_page(){
    String targetedUrl = "https://www.saucedemo.com/inventory.html";
    String currentUrl = driver.getCurrentUrl();
    Assert.assertEquals(currentUrl, targetedUrl);
  }

  @And("close browser")
  public void close_browser(){    
    driver.close();
    driver.quit();
  }
}
